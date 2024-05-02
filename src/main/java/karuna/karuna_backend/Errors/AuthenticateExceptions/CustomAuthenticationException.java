package karuna.karuna_backend.Errors.AuthenticateExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;

public class CustomAuthenticationException extends CustomException {

    public CustomAuthenticationException(String key, String desc) {
            super(key, desc);
        }

        @Override
        public AuthenticationErrorResponse mapToErrorResponse(){
            return new AuthenticationErrorResponse(super.getKey(), super.getDescription());
    }

}
