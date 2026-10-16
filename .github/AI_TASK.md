# AI maintenance task for the Marketplace repository

You are making a small, reviewable maintenance change to this Java 21 / Spring Boot Maven monorepo.

## Operating rules
- Work only on the current automation branch. Never deploy, publish images, provision cloud resources, change secrets, or run Terraform apply.
- Read the relevant source and tests before editing. Prefer one coherent, low-risk improvement per run.
- Prioritize in this order: fix an objectively broken test/build/configuration issue; add or improve a focused test for existing behavior; improve a documented validation or reliability gap.
- Do not make broad rewrites, invent business requirements, add credentials, or weaken tests, security checks, formatting, or quality gates just to make CI pass.
- Do not modify this file or the AI automation workflow unless required to fix a concrete defect in the automation itself.
- Do not modify generated files, dependency lockfiles, or deployment/production settings unless the selected task genuinely requires it.
- Keep the diff small (ideally at most 5 files). If no safe, evidence-based improvement is clear, make no changes and explain why.
- Run the narrowest relevant tests/checks available. Do not claim checks passed unless you actually ran them.
- Leave all changes for human review in a pull request. Never push directly to main or auto-merge.
- End by summarizing the issue, files changed, checks run, known limitations, and any follow-up needed.