package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines validation error types. " +
        "SIZE_INVALID: Invalid size of field. " +
        "PATTERN_INVALID: Invalid pattern of field. " +
        "EMAIL_INVALID: Invalid email format. " +
        "MORE_THAN_MAX: Value exceeds maximum allowed. " +
        "MIN_NOT_REACHED: Value does not meet minimum required. " +
        "INVALID_NULL_FIELD: Field cannot be null. " +
        "INVALID_BLANK_FIELD: Field cannot be blank. " +
        "UNKNOWN: Unknown validation error."
)
public enum FailedValidationErrorKey {
    SIZE_INVALID,
    PATTERN_INVALID,
    EMAIL_INVALID,
    MORE_THAN_MAX,
    MIN_NOT_REACHED,
    INVALID_NULL_FIELD,
    INVALID_BLANK_FIELD,
    UNKNOWN
}
