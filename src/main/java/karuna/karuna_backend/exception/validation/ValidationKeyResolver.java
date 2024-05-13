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
                    return FailedValidationErrorKey.SIZE;
                case "notblank":
                    return FailedValidationErrorKey.NOT_BLANK;
                case "notnull":
                    return FailedValidationErrorKey.NOT_NULL;
                case "min":
                    return FailedValidationErrorKey.MIN;
                case "max":
                    return FailedValidationErrorKey.MAX;
                case "email":
                    return FailedValidationErrorKey.EMAIL;
                case "pattern":
                    return FailedValidationErrorKey.PATTERN;
            }
        }
        return FailedValidationErrorKey.UNKNOWN;
    }
}
