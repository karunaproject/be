package karuna.karuna_backend.user.exception;

import karuna.karuna_backend.exception.CustomException;
import karuna.karuna_backend.exception.CustomExceptionKey;
import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.dto.CustomErrorResponse;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(String message) {
        super(CustomExceptionKey.USER_NOT_FOUND, message);
    }

    @Override
    public IErrorResponse mapToErrorResponse() {
        return new CustomErrorResponse(CustomExceptionKey.USER_NOT_FOUND, getMessage());
    }
}
