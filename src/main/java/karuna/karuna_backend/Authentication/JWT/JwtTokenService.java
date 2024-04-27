package karuna.karuna_backend.Authentication.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenService {

    private final JwtConfig jwtConfig;
    private Key signingKey;
    private long tokenExpiration;
    private long refreshTokenExpiration;

    @Autowired
    public JwtTokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    private void init() {
        SignatureAlgorithm algorithm = SignatureAlgorithm.forName(jwtConfig.getSignatureAlgorithm());

        // Initialize cached values and resources
        this.signingKey = Keys.secretKeyFor(algorithm);
        this.tokenExpiration = jwtConfig.getTokenExpirationTime();
        this.refreshTokenExpiration = jwtConfig.getRefreshTokenExpirationTime();
    }

    public String generateToken(String username, Map<String, Object> additionalClaims) {
        //TODO: Validate username
        return buildToken(username,  tokenExpiration, additionalClaims);
    }

    public String generateToken(Authentication authentication) {
        //TODO: Validate username
        return buildToken(authentication.getName(),  tokenExpiration);
    }

    public String generateRefreshToken(Authentication authentication) {
        //TODO: Validate username
        return buildToken(authentication.getName(), refreshTokenExpiration);
    }

    private String buildToken(String username, long expirationMillis, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(signingKey);

        claims.forEach(jwtBuilder::claim);

        return jwtBuilder.compact();
    }

    private String buildToken(String username, long expirationMillis) {
        long now = System.currentTimeMillis();
        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(signingKey);

        return jwtBuilder.compact();
    }

}
