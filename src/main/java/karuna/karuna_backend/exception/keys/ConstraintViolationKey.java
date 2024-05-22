package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines constrain violation error types. " +
        "INVALID_EMAIL_PATTERN: invalid email pattern. " +
        "LENGTH_IS_INCORRECT: Invalid length of variable. " +
        "UNKNOWN_ERROR: An unknown error occurred.")
public enum ConstraintViolationKey {
    UNKNOWN_ERROR,
    INVALID_EMAIL_PATTERN,
    LENGTH_IS_INCORRECT
}
