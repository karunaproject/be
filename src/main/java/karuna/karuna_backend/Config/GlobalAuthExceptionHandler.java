package karuna.karuna_backend.Config;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.Authentication.AuthController;
import karuna.karuna_backend.Errors.AuthenticateExceptions.LoginPasswordAuthenticationException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.DataIntegrityErrorResponse;
import karuna.karuna_backend.Errors.DatabaseExceptions.DatabaseExceptionHandler;
import karuna.karuna_backend.Errors.DatabaseExceptions.DatabaseIntegrityException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* !!!!!! ControllerAdvice class is handled GLOBALLY IN SWAGGER, every single ExceptionHandler will be
 updated in swagger docs even if specific endpoint does not return every single status codes provided below !!!!!!! */
@RestControllerAdvice(assignableTypes = AuthController.class)
@AllArgsConstructor
public class GlobalAuthExceptionHandler {
    private final DatabaseExceptionHandler databaseExceptionHandler;

    @ResponseStatus(HttpStatus.CONFLICT)
    @Hidden
    @ExceptionHandler(DataIntegrityViolationException.class)
    public DataIntegrityErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        DatabaseIntegrityException databaseException = databaseExceptionHandler.handleIntegrityException(ex);

        return databaseException.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Hidden
    @ExceptionHandler(LoginPasswordAuthenticationException.class)
    public AuthenticationErrorResponse handleCustomUsernameNotFoundEx(LoginPasswordAuthenticationException ex){
        return ex.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleGenericException(RuntimeException ex) {
        return "An error occurred" + ex.getMessage();
    }
}
