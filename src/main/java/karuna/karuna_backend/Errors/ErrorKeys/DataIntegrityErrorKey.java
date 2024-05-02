package karuna.karuna_backend.Errors.ErrorKeys;

import io.swagger.v3.oas.annotations.media.Schema;

public enum DataIntegrityErrorKey {
    @Schema(description = "Indicates an error type that is not defined or unknown.")
    UNKNOWN_ERROR,
    @Schema(description = "Indicates a failure due to a violation of a unique constraint in the database.")
    UNIQUE_CONSTRAINT_VIOLATION,
    @Schema(description = "Indicates a failure due to a non-null constraint being violated.")
    NOT_NULL_CONSTRAINT_VIOLATION
}
