package karuna.karuna_backend.Errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class DatabaseExceptionHandler extends DatabaseErrorResolver {

    private final DatabaseErrorResolver errorResolver;

    public DatabaseExceptionHandler(DatabaseErrorResolver errorResolver) {
        this.errorResolver = errorResolver;
    }

    public CustomException handleIntegrityException(DataIntegrityViolationException e){
        //TODO: Refactor into a little bit more flexible version
        DatabaseErrorResolver.ErrorCode errorCode = errorResolver.resolveErrorCode(e);
        if (errorCode == ErrorCode.UNIQUE_CONSTRAINT_VIOLATION) {
            return new CustomException("unique_constraint_violation",
                    "Unique key constraint was violated during query execution, record probably already exists in the database");
        }
        if (errorCode == ErrorCode.NOT_NULL_CONSTRAINT_VIOLATION) {
            return new CustomException("not_null_constraint_violation",
                    "Required field cannot be null");
        }
        // Handle other error codes as needed
        return new CustomException("unknown_database_error", "Unknown database error occurred during executing the query");
    }
}

