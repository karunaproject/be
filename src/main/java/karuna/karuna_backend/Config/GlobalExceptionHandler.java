package karuna.karuna_backend.Config;

import karuna.karuna_backend.Errors.AuthenticateExceptions.CustomAuthenticationException;
import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DatabaseExceptions.DatabaseExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    private final DatabaseExceptionHandler databaseExceptionHandler;

    @Autowired
    public GlobalExceptionHandler(DatabaseExceptionHandler databaseExceptionHandler){
        this.databaseExceptionHandler = databaseExceptionHandler;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        CustomException customException = databaseExceptionHandler.handleIntegrityException(ex);

        return new ResponseEntity<>(customException.mapToDao(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<Object> handleCustomUsernameNotFoundEx(CustomAuthenticationException ex){

        return new ResponseEntity<>(ex.mapToDao(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
