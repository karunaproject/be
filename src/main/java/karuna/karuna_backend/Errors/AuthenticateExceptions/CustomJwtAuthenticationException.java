package karuna.karuna_backend.Errors.AuthenticateExceptions;

import io.jsonwebtoken.JwtException;
import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomJwtAuthenticationException{
    private final String key;
    private final String description;
    public CustomJwtAuthenticationException(String key, String desc) {
       this.description=desc;
       this.key=key;
    }

    public AuthenticationErrorResponse mapToErrorResponse(){
        return new AuthenticationErrorResponse(key, description);
    }
}
