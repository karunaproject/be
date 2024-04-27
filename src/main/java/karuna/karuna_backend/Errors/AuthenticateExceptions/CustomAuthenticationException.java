package karuna.karuna_backend.Errors.AuthenticateExceptions;

import karuna.karuna_backend.Errors.CustomException;

public class CustomAuthenticationException extends CustomException {

    public CustomAuthenticationException(String key, String desc) {
        super(key, desc);
    }

}
