package karuna.karuna_backend.exception.validation;

import karuna.karuna_backend.exception.keys.FailedValidationErrorKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationKeyResolver {

    static FailedValidationErrorKey getKey(MethodArgumentNotValidException methodArgumentNotValidException) {
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        String annotationType = fieldError != null ? fieldError.getCode() : null;
        if (Objects.nonNull(annotationType)) {
            switch (annotationType.toLowerCase()) {
                case "size":
                    return FailedValidationErrorKey.INVALID_SIZE;
                case "notblank":
                    return FailedValidationErrorKey.VALUE_IS_BLANK;
                case "notnull":
                    return FailedValidationErrorKey.VALUE_IS_NULL;
                case "min":
                    return FailedValidationErrorKey.VALUE_TOO_SHORT;
                case "max":
                    return FailedValidationErrorKey.VALUE_TOO_LONG;
                case "email":
                    return FailedValidationErrorKey.INVALID_EMAIL_PATTERN;
                case "pattern":
                    return FailedValidationErrorKey.INVALID_PATTERN;
            }
        }
        return FailedValidationErrorKey.UNKNOWN;
    }
}
