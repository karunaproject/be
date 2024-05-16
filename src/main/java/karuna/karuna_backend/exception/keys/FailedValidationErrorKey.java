package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines validation error types. " +
        "INVALID_SIZE: Invalid size of field. " +
        "INVALID_PATTERN: Invalid pattern of field. " +
        "INVALID_EMAIL_PATTERN: Invalid email format. " +
        "VALUE_TOO_LONG: Value exceeds maximum allowed. " +
        "VALUE_TOO_SHORT: Value does not meet minimum required. " +
        "VALUE_IS_NULL: Field cannot be null. " +
        "VALUE_IS_BLANK: Field cannot be blank. " +
        "UNKNOWN: Unknown validation error."
)
public enum FailedValidationErrorKey {
    INVALID_SIZE,
    INVALID_PATTERN,
    INVALID_EMAIL_PATTERN,
    VALUE_TOO_LONG,
    VALUE_TOO_SHORT,
    VALUE_IS_NULL,
    VALUE_IS_BLANK,
    UNKNOWN
}
