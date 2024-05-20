package karuna.karuna_backend.exception.constraintViolation;

import jakarta.validation.ConstraintViolationException;
import karuna.karuna_backend.exception.dto.ConstraintViolationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ConstraintViolationResponse handleConstraintViolation(ConstraintViolationException constraintViolationException) {
        String message = constraintViolationException.getConstraintViolations().stream().toList().getFirst().getMessage();
        return new ConstraintViolationResponse(ConstraintViolationKeyResolver.getKey(message), message);

    }
}
