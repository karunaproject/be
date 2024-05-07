package karuna.karuna_backend.user.exception;

import karuna.karuna_backend.exception.dto.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleUserNotFound(UserNotFoundException userNotFoundException) {
        return (CustomErrorResponse) userNotFoundException.mapToErrorResponse();
    }
}
