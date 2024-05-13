package karuna.karuna_backend.exception.validation;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.exception.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
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
    ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ValidationErrorResponse(ValidationKeyResolver.getKey(methodArgumentNotValidException), message);
    }
}
