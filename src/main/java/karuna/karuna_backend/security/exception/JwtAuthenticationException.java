package karuna.karuna_backend.security.exception;

import karuna.karuna_backend.exception.CustomException;
import karuna.karuna_backend.exception.dto.JwtErrorResponse;
import karuna.karuna_backend.exception.keys.JwtErrorKey;

public class JwtAuthenticationException extends CustomException {
    public JwtAuthenticationException(JwtErrorKey key, String desc) {
        super(key, desc);
    }

    @Override
    public JwtErrorResponse mapToErrorResponse() {

        //TODO: Think of the way to make CustomException Generic and still extend Throwable Java.
        if (!(getKey() instanceof JwtErrorKey)) {
            throw new IllegalStateException("Key type mismatch in JwtException");
        }
        return new JwtErrorResponse((JwtErrorKey) getKey(), getDescription());
    }
}
