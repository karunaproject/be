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
                    return FailedValidationErrorKey.SIZE_INVALID;
                case "notblank":
                    return FailedValidationErrorKey.INVALID_BLANK_FIELD;
                case "notnull":
                    return FailedValidationErrorKey.INVALID_NULL_FIELD;
                case "min":
                    return FailedValidationErrorKey.MIN_NOT_REACHED;
                case "max":
                    return FailedValidationErrorKey.MORE_THAN_MAX;
                case "email":
                    return FailedValidationErrorKey.EMAIL_INVALID;
                case "pattern":
                    return FailedValidationErrorKey.PATTERN_INVALID;
            }
        }
        return FailedValidationErrorKey.UNKNOWN;
    }
}
