package karuna.karuna_backend.Config;

import jakarta.persistence.ElementCollection;
import karuna.karuna_backend.Errors.AuthenticateExceptions.CustomAuthenticationException;
import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import karuna.karuna_backend.Errors.DTO.DataIntegrityErrorResponse;
import karuna.karuna_backend.Errors.DatabaseExceptions.DatabaseExceptionHandler;
import karuna.karuna_backend.Errors.DatabaseExceptions.DatabaseIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    private final DatabaseExceptionHandler databaseExceptionHandler;

    @Autowired
    public GlobalExceptionHandler(DatabaseExceptionHandler databaseExceptionHandler){
        this.databaseExceptionHandler = databaseExceptionHandler;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public DataIntegrityErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        DatabaseIntegrityException databaseException = databaseExceptionHandler.handleIntegrityException(ex);

        return databaseException.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomAuthenticationException.class)
    public AuthenticationErrorResponse handleCustomUsernameNotFoundEx(CustomAuthenticationException ex){
        return ex.mapToErrorResponse();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex) {
        return "An error occurred" + ex.getMessage();
    }
}
