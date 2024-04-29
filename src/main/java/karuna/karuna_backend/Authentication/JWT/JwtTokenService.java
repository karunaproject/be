package karuna.karuna_backend.Authentication.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.Utils.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @SafeVarargs
    public final String generateToken(CustomUserDetails user, Map<String, Object>... additionalClaims) {
        if (additionalClaims.length > 0) {
            return buildToken(user, tokenExpiration, additionalClaims[0]);
        } else {
            return buildToken(user, tokenExpiration);
        }
    }

    public String generateRefreshToken(CustomUserDetails user) {
        return buildToken(user, refreshTokenExpiration);
    }

    @SafeVarargs
    private String buildToken(CustomUserDetails user, Duration expirationDuration, Map<String, Object>... additionalClaims) {
        long userId = user.getId();
        String username = user.getUsername();
        long now = System.currentTimeMillis();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationDuration.toMillis()))
                .signWith(signingKey);

        // If additional claims are provided, add them to the token
        if (additionalClaims.length > 0) {
            Map<String, Object> claims = additionalClaims[0];
            claims.forEach(jwtBuilder::claim);
        }

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

        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        String username = claimsJws.getBody().getSubject();
        List<String> roles = claimsJws.getBody().get("roles", List.class);

        SecurityContextUtil.setSecurityContext(username, roles);
    }

}
