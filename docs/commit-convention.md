# Git Commit Message Convention

For the ***focus*** project, use [Conventional Commits](https://www.conventionalcommits.org/) to keep the Git history clean, consistent, and easy to understand.

## Format

`<type>(<scope>): <short description>`

Example: `feat(auth): add JWT authentication`

## Commit Types

| Type | Use for | Example |
|---|---|---|
| `feat` | New functionality | `feat(timer): add pause functionality` |
| `fix` | Bug fixes | `fix(auth): reject expired refresh tokens` |
| `refactor` | Code restructuring without behavior changes | `refactor(user): extract validation logic` |
| `test` | Adding or changing tests | `test(category): add repository tests` |
| `docs` | Documentation changes | `docs(api): document authentication endpoints` |
| `chore` | Maintenance and project tooling | `chore(deps): update dependencies` |
| `style` | Formatting changes with no logic changes | `style: format entity classes` |
| `perf` | Performance improvements | `perf(stats): cache aggregate calculations` |
| `build` | Build system changes | `build: configure PostgreSQL driver` |
| `ci` | CI/CD changes | `ci: add GitHub Actions workflow` |

## General Guidelines

- Keep commits focused on one logical change.
- Keep the subject short and descriptive.
- Use the imperative form (`add`, `fix`, `implement`) rather than past tense (`added`, `fixed`, `implemented`).
- Use a scope when it helps identify the affected feature or domain.
- Don't use `feat` for every change; choose the type that accurately describes the commit.
- Avoid unnecessary implementation details in the commit subject.
- Prefer meaningful commits over very small, fragmented commits.