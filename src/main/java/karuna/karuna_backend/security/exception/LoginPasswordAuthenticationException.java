package karuna.karuna_backend.security.exception;

import karuna.karuna_backend.exception.CustomException;
import karuna.karuna_backend.exception.dto.AuthenticationErrorResponse;
import karuna.karuna_backend.exception.keys.LoginPasswordAuthErrorKey;

public class LoginPasswordAuthenticationException extends CustomException {

    public LoginPasswordAuthenticationException(LoginPasswordAuthErrorKey key, String desc) {
            super(key, desc);
        }

        @Override
        public AuthenticationErrorResponse mapToErrorResponse(){
            //TODO: Think of the way to make CustomException Generic and still extend Throwable Java.
            if (!(getKey() instanceof LoginPasswordAuthErrorKey)) {
                throw new IllegalStateException("Key type mismatch in LoginPasswordAuthenticationException");
            }
            return new AuthenticationErrorResponse((LoginPasswordAuthErrorKey) getKey(), getDescription());
    }

}
