package karuna.karuna_backend.Errors.AuthenticateExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.ErrorKeys.LoginPasswordAuthErrorKey;

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
