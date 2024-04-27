package karuna.karuna_backend.Authentication.JWT;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String signatureAlgorithm; // Defaulting to HS256
    private String secretKey ;
    private String tokenPrefix;
    private int tokenExpirationTime;
    private int refreshTokenExpirationTime;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
