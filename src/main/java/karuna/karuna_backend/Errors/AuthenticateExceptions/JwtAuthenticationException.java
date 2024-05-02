package karuna.karuna_backend.Errors.AuthenticateExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.JwtErrorResponse;
import karuna.karuna_backend.Errors.ErrorKeys.JwtErrorKey;

public class JwtAuthenticationException extends CustomException {
    public JwtAuthenticationException(JwtErrorKey key, String desc) {
        super(key, desc);
    }

    @Override
    public JwtErrorResponse mapToErrorResponse() {
        return new JwtErrorResponse(super.getKey(), super.getDescription());
    }
}
