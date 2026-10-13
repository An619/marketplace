package com.enterprise.marketplace.identityservice.infrastructure.redis;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.enterprise.marketplace.identityservice.config.AuthProperties;

import lombok.RequiredArgsConstructor;

/** Redis-backed auth session store for shared, TTL-bound authentication state. */
@Component
@ConditionalOnProperty(
        prefix = "marketplace.auth",
        name = "session-store",
        havingValue = "redis",
        matchIfMissing = true)
@RequiredArgsConstructor
public class RedisAuthSessionStore implements AuthSessionStore {

    private static final String OTP_PREFIX = "marketplace:auth:otp:";
    private static final String VERIFICATION_PREFIX = "marketplace:auth:verification:";
    private static final String ACCESS_PREFIX = "marketplace:auth:access:";
    private static final String QR_PREFIX = "marketplace:auth:qr:";

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final AuthProperties authProperties;

    @Override
    public void saveOtpSession(OtpSessionData data) {
        put(OTP_PREFIX + data.getSessionId(), data, authProperties.getOtp().getTtlSeconds());
    }

    @Override
    public Optional<OtpSessionData> getOtpSession(String sessionId) {
        return get(OTP_PREFIX + sessionId, OtpSessionData.class);
    }

    @Override
    public void deleteOtpSession(String sessionId) {
        redisTemplate.delete(OTP_PREFIX + sessionId);
    }

    @Override
    public String saveVerificationToken(VerificationTokenData data) {
        if (data.getToken() == null || data.getToken().isBlank()) {
            data.setToken(UUID.randomUUID().toString());
        }
        put(
                VERIFICATION_PREFIX + data.getToken(),
                data,
                authProperties.getToken().getVerificationTtlSeconds());
        return data.getToken();
    }

    @Override
    public Optional<VerificationTokenData> getVerificationToken(String token) {
        return get(VERIFICATION_PREFIX + token, VerificationTokenData.class);
    }

    @Override
    public String saveAccessToken(AccessTokenData data) {
        if (data.getToken() == null || data.getToken().isBlank()) {
            data.setToken(UUID.randomUUID().toString());
        }
        put(ACCESS_PREFIX + data.getToken(), data, authProperties.getToken().getAccessTtlSeconds());
        return data.getToken();
    }

    @Override
    public Optional<AccessTokenData> getAccessToken(String token) {
        return get(ACCESS_PREFIX + token, AccessTokenData.class);
    }

    @Override
    public void saveQrSession(QrSessionData data) {
        put(QR_PREFIX + data.getQrSessionId(), data, authProperties.getQr().getTtlSeconds());
    }

    @Override
    public Optional<QrSessionData> getQrSession(String qrSessionId) {
        return get(QR_PREFIX + qrSessionId, QrSessionData.class);
    }

    private void put(String key, Object value, long ttlSeconds) {
        try {
            redisTemplate.opsForValue().set(
                    key,
                    objectMapper.writeValueAsString(value),
                    Duration.ofSeconds(Math.max(1, ttlSeconds)));
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Unable to serialize authentication session data", exception);
        }
    }

    private <T> Optional<T> get(String key, Class<T> type) {
        String json = redisTemplate.opsForValue().get(key);
        if (json == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(objectMapper.readValue(json, type));
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Unable to deserialize authentication session data", exception);
        }
    }
}
