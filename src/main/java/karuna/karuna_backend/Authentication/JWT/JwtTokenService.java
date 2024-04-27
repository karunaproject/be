package karuna.karuna_backend.Authentication.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Models.User;
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

    public String generateToken(CustomUserDetails user, Map<String, Object> additionalClaims) {
        return buildToken(user,  tokenExpiration, additionalClaims);
    }

    public String generateToken(CustomUserDetails user) {
        return buildToken(user,  tokenExpiration);
    }

    public String generateRefreshToken(CustomUserDetails user) {
        return buildToken(user, refreshTokenExpiration);
    }

    private String buildToken(CustomUserDetails user, long expirationMillis, Map<String, Object> claims) {
        long userId = user.getId();
        String username = user.getUsername();
        long now = System.currentTimeMillis();

        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(signingKey);

        claims.forEach(jwtBuilder::claim);

        return jwtBuilder.compact();
    }

    private String buildToken(CustomUserDetails user, long expirationMillis) {
        long userId = user.getId();
        String username = user.getUsername();
        long now = System.currentTimeMillis();

        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(signingKey);

        return jwtBuilder.compact();
    }

}
