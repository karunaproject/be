package karuna.karuna_backend.exception.validation;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.exception.dto.CustomErrorResponse;
import karuna.karuna_backend.exception.keys.FailedValidationErrorKey;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
class GlobalValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Hidden
    CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new CustomErrorResponse(getKey(methodArgumentNotValidException), message);
    }

    private FailedValidationErrorKey getKey(MethodArgumentNotValidException methodArgumentNotValidException) {
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
