package karuna.karuna_backend.Errors;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines data integrity error types. "
 + "UNIQUE_CONSTRAINT_VIOLATION: Indicates a failure due to a violation of unique constraint in the database")
public enum CustomExceptionKey {
    UNIQUE_CONSTRAINT_VIOLATION
}
