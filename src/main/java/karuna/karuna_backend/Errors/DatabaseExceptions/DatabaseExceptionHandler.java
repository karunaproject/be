package karuna.karuna_backend.Errors.DatabaseExceptions;

import karuna.karuna_backend.Errors.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExceptionHandler{

    private final DatabaseErrorResolver errorResolver;

    public DatabaseExceptionHandler(DatabaseErrorResolver errorResolver) {
        this.errorResolver = errorResolver;
    }

    public CustomException handleIntegrityException(DataIntegrityViolationException e){
        //TODO: Refactor into a little bit more flexible version
        DatabaseErrorResolver.ErrorCode errorCode = errorResolver.resolveErrorCode(e);
        return switch (errorCode) {
            case UNIQUE_CONSTRAINT_VIOLATION -> new CustomException("unique_constraint_violation",
                    "Unique key was violated during query execution, record probably already exists in the database");
            case NOT_NULL_CONSTRAINT_VIOLATION -> new CustomException("not_null_constraint_violation",
                    "Required field cannot be null");
            default -> new CustomException("unknown_database_error",
                    "Unknown database error occurred during executing the query");
        };
    }
}

