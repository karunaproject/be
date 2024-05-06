package karuna.karuna_backend.user.exception;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.exception.database.DatabaseExceptionHandler;
import karuna.karuna_backend.exception.database.DatabaseIntegrityException;
import karuna.karuna_backend.exception.dto.AuthenticationErrorResponse;
import karuna.karuna_backend.exception.dto.DataIntegrityErrorResponse;
import karuna.karuna_backend.security.exception.LoginPasswordAuthenticationException;
import karuna.karuna_backend.user.infrastructure.AuthController;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* !!!!!! ControllerAdvice class is handled GLOBALLY IN SWAGGER, every single ExceptionHandler will be
 updated in swagger docs even if specific endpoint does not return every single status codes provided below !!!!!!! */
@RestControllerAdvice(assignableTypes = AuthController.class)
@RequiredArgsConstructor
class GlobalAuthExceptionHandler {
    private final DatabaseExceptionHandler databaseExceptionHandler;

    @ResponseStatus(HttpStatus.CONFLICT)
    @Hidden
    @ExceptionHandler(DataIntegrityViolationException.class)
    DataIntegrityErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        DatabaseIntegrityException databaseException = databaseExceptionHandler.handleIntegrityException(ex);

        return databaseException.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Hidden
    @ExceptionHandler(LoginPasswordAuthenticationException.class)
    AuthenticationErrorResponse handleCustomUsernameNotFoundEx(LoginPasswordAuthenticationException ex){
        return ex.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    String handleGenericException(RuntimeException ex) {
        return "An error occurred" + ex.getMessage();
    }
}
