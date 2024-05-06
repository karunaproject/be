package karuna.karuna_backend.exception;

import karuna.karuna_backend.exception.dto.CustomErrorResponse;
import karuna.karuna_backend.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new CustomErrorResponse(CustomExceptionKey.UNIQUE_CONSTRAINT_VIOLATION, methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleUserNotFound(UserNotFoundException userNotFoundException) {
        return (CustomErrorResponse) userNotFoundException.mapToErrorResponse();
    }
}
