package karuna.karuna_backend.Errors.UserExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.CustomExceptionKey;
import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import karuna.karuna_backend.Errors.IErrorResponse;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(String message) {
        super(CustomExceptionKey.USER_NOT_FOUND, message);
    }

    @Override
    public IErrorResponse mapToErrorResponse() {
        return new CustomErrorResponse(CustomExceptionKey.USER_NOT_FOUND, getMessage());
    }
}
