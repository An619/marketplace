package com.enterprise.marketplace.adminservice.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConfigRequest {

    @NotNull private Map<String, Object> configValue;

    @Size(max = 64) private String scope;

    private Boolean active;
}
