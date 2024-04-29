package karuna.karuna_backend.Authentication.JWT;

import groovyjarjarasm.asm.signature.SignatureReader;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenService {

    private final JwtConfig jwtConfig;
    private Key signingKey;
    private Duration tokenExpiration;
    private Duration refreshTokenExpiration;

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

    private String buildToken(CustomUserDetails user, Duration expirationDuration, Map<String, Object> claims) {
        long userId = user.getId();
        String username = user.getUsername();
        long now = System.currentTimeMillis();

        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationDuration.toMillis()))
                .signWith(signingKey);

        claims.forEach(jwtBuilder::claim);


        return jwtBuilder.compact();
    }

    private String buildToken(CustomUserDetails user, Duration expirationDuration) {
        long userId = user.getId();
        String username = user.getUsername();
        long now = System.currentTimeMillis();

        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationDuration.toMillis()))
                .signWith(signingKey);

        return jwtBuilder.compact();
    }

    public void verifyToken(String token) throws SignatureException, ExpiredJwtException, MalformedJwtException{
        //TODO: Implement -> refresh token logic. Refresh token should be used to extend normal token life time.
        //TODO: Implement -> Logout and blacklisting tokens, either way blacklist tokens in database or redis cache. Ideally use refresh tokens as blacklsited ones in order
        // to mitigate database overload.
        //TODO: Manually check the vulnerability to token attacks (Changing token structure). Execute tests if this method properly handles token-based attacks.
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();

        String username = jwtParser.parseClaimsJws(token).getBody().getSubject();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null); // Authorities would be added here
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
