package karuna.karuna_backend.exception.validation;

import karuna.karuna_backend.exception.CustomExceptionKey;
import karuna.karuna_backend.exception.dto.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new CustomErrorResponse(CustomExceptionKey.UNIQUE_CONSTRAINT_VIOLATION, methodArgumentNotValidException.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
    }
}
