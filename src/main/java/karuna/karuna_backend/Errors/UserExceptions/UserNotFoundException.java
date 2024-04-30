package karuna.karuna_backend.Errors.UserExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.CustomExceptionKey;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(String message) {
        super(CustomExceptionKey.USER_NOT_FOUND, message);
    }
}
