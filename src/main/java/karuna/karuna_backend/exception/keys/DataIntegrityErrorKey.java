package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines data integrity error types. " +
        "UNKNOWN_ERROR: Indicates an error type that is not defined or unknown. " +
        "UNIQUE_CONSTRAINT_VIOLATION: Indicates a failure due to a violation of a unique constraint in the database. " +
        "NOT_NULL_CONSTRAINT_VIOLATION: Indicates a failure due to a non-null constraint being violated.")
public enum DataIntegrityErrorKey {
    UNKNOWN_ERROR,
    UNIQUE_CONSTRAINT_VIOLATION,
    NOT_NULL_CONSTRAINT_VIOLATION
}
