package karuna.karuna_backend.Errors;

import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import karuna.karuna_backend.Errors.UserExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleUserNotFound(UserNotFoundException userNotFoundException) {
        return userNotFoundException.mapToErrorResponse();
    }
}
