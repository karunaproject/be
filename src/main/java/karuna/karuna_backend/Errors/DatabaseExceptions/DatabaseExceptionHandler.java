package karuna.karuna_backend.Errors.DatabaseExceptions;

import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;


/**
 * Handles exceptions related to database integrity, converting {@link DataIntegrityViolationException}
 * into more specific {@link DatabaseIntegrityException} instances based on the error code resolved.
 */
@Component
public class DatabaseExceptionHandler{

    private final DatabaseErrorResolver errorResolver;

    public DatabaseExceptionHandler(DatabaseErrorResolver errorResolver) {
        this.errorResolver = errorResolver;
    }


    /**
     * Handles a {@link DataIntegrityViolationException} by resolving the specific error code
     * and returning a corresponding {@link DatabaseIntegrityException}.
     *
     * @param e The {@link DataIntegrityViolationException} to handle.
     * @return A {@link DatabaseIntegrityException} that represents a more specific error based on the resolved code.
     */
    public DatabaseIntegrityException handleIntegrityException(DataIntegrityViolationException e){
        //TODO: Refactor into a little bit more flexible version
        DataIntegrityErrorKey errorCode = errorResolver.resolveErrorCode(e);
        return switch (errorCode) {
            case UNIQUE_CONSTRAINT_VIOLATION -> new DatabaseIntegrityException(errorCode,
                    "Unique key was violated during query execution, record probably already exists in the database");
            case NOT_NULL_CONSTRAINT_VIOLATION -> new DatabaseIntegrityException(errorCode,
                    "Required field cannot be null");
            default -> new DatabaseIntegrityException(errorCode,
                    "Unknown database error occurred during executing the query");
        };
    }
}

