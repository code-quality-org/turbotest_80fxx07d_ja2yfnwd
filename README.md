# Code Coverage Workflow Example

Demonstrates how to generate code coverage for a Java project and upload it to
GitHub using the code coverage API.

## How it works

1. **Build & test** — Maven runs JUnit tests with the JaCoCo plugin, which
   generates a coverage report at `target/site/jacoco/jacoco.xml`.

2. **Convert to Cobertura** — The upload endpoint expects Cobertura XML format.
   A vendored copy of [cover2cover](https://github.com/rix0rrr/cover2cover)
   converts the JaCoCo report.

3. **Upload** — The [workflow](https://github.com/code-quality-org/joshhale-code-coverage-workflow/blob/main/.github/workflows/coverage.yml) calls `PUT /repos/{owner}/{repo}/code-coverage/report`
   via `gh api`, sending the base64-encoded Cobertura report along with the
   commit SHA, ref, language, and (on PRs) the pull request number.

## Project structure

```
.github/workflows/coverage.yml   ← GitHub Actions workflow
pom.xml                           ← Maven build with JaCoCo plugin
scripts/cover2cover.py            ← JaCoCo-to-Cobertura converter (MIT, vendored)
src/main/java/                    ← Example Java source
src/test/java/                    ← Example JUnit 5 tests
```

## Upload endpoint

```
PUT /repos/{owner}/{repo}/code-coverage/report
```

| Field                  | Type    | Required | Description                                    |
|------------------------|---------|----------|------------------------------------------------|
| `commit_oid`           | string  | yes      | 40-character commit SHA                        |
| `ref`                  | string  | yes      | Git ref (e.g. `refs/heads/main`)               |
| `coverage_report`      | string  | yes      | Base64-encoded Cobertura XML                   |
| `language_name`        | string  | yes      | Linguist language name (e.g. `Java`)           |
| `label`                | string  | yes      | Report identifier (e.g. `code-coverage/jacoco`)|
| `pull_request_number`  | integer | no       | PR number, if uploading for a pull request     |

## Prerequisites

- The repository must belong to an **organization** with the `code_coverage_upload_api`
  feature flag enabled.
- The workflow token needs `security-events: write` permission (will change to
  `code-quality: write` in the future).

## Running locally

```sh
mvn verify
python3 scripts/cover2cover.py target/site/jacoco/jacoco.xml src/main/java/ > cobertura.xml
```
