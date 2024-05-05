package karuna.karuna_backend.Authentication.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.Utils.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service to handle JWT operations including token generation, parsing, and validation.
 * This service uses configuration provided by {@link JwtConfig} to initialize and manage JWTs.
 */
@Service
public class JwtTokenService {

    private static final ZoneId TIME_ZONE = ZoneId.of("UTC");
    private final JwtConfig jwtConfig;
    private Key signingKey;
    private Duration tokenExpiration;

    @Autowired
    public JwtTokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        init();
    }


    /**
     * Initializes the JWT settings, including signing key and token expiration times.
     * This method is automatically invoked after the service's properties are set.
     */
    private void init() {
        try {
            String secretKey = jwtConfig.getSecretKey();
            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

            this.signingKey = Keys.hmacShaKeyFor(keyBytes);
            this.tokenExpiration = jwtConfig.getTokenExpirationTime();
            //TODO: Handle global catch/logger
        } catch (WeakKeyException ex) {
            throw new WeakKeyException("Secure key is too short");
        }
    }

    /**
     * Generates a JWT for a specified user. Can include additional claims if provided.
     *
     * @param user              The user details for whom the token is generated.
     * @param additionalClaims  Additional claims to be included in the JWT.
     * @return                  A JWT string representing the user's session.
     */
    @SafeVarargs
    public final String generateToken(CustomUserDetails user, Map<String, Object>... additionalClaims) {
        OffsetDateTime now = OffsetDateTime.now(TIME_ZONE);
        OffsetDateTime expirationTime = now.plus(tokenExpiration.toMillis(), ChronoUnit.MILLIS);

        if (additionalClaims.length > 0) {
            return buildToken(user, now, expirationTime, additionalClaims[0]);
        } else {
            return buildToken(user, now, expirationTime);
        }
    }

    /**
     * Internal method to build a JWT given the user details and token expiration.
     *
     * @param user               The user details for whom the token is generated.
     * @param issuedAt           Time when token was created.
     * @param expiresAt          Time when token expires.
     * @param additionalClaims   Optional additional claims to include in the token.
     * @return                   A JWT string.
     */
    @SafeVarargs
    private String buildToken(CustomUserDetails user, OffsetDateTime issuedAt, OffsetDateTime expiresAt, Map<String, Object>... additionalClaims) {
        var jwtBuilder = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(Date.from(issuedAt.toInstant()))
                .setExpiration(Date.from(expiresAt.toInstant()))
                .signWith(signingKey);

        // If additional claims are provided, add them to the token
        if (additionalClaims.length > 0) {
            Map<String, Object> claims = additionalClaims[0];
            claims.forEach(jwtBuilder::claim);
        }

        return jwtBuilder.compact();
    }

    /**
     * Verifies the JWT, checks its signature and parses the claims.
     *
     * @param token The JWT to be verified.
     * @throws SignatureException if the JWT signature does not match.
     * @throws ExpiredJwtException if the JWT has expired.
     * @throws MalformedJwtException if the JWT is not correctly structured.
     */
    public void verifyToken(String token) throws SignatureException, ExpiredJwtException, MalformedJwtException{
        //TODO: Implement -> refresh token logic. Refresh token should be used to extend normal token life time.
        //TODO: Implement -> Logout and blacklisting tokens, either way blacklist tokens in database or redis cache. Ideally use refresh tokens as blacklsited ones in order
        // to mitigate database overload.
        //TODO: Manually check the vulnerability to token attacks (Changing token structure). Execute tests if this method properly handles token-based attacks.
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();

        //This method throws mentioned above errors.
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        String username = claimsJws.getBody().getSubject();
        List<String> roles = claimsJws.getBody().get("roles", List.class);

        SecurityContextUtil.setSecurityContext(username, roles);
    }

    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public OffsetDateTime getExpirationDate(String token) {
        Date expirationDate = getAllClaims(token).getExpiration();
        return expirationDate.toInstant().atZone(TIME_ZONE).toOffsetDateTime();
    }


    public String getSubject(String token) {
        return getAllClaims(token).getSubject();
    }


    public List<String> getRoles(String token) {
        return getAllClaims(token).get("roles", List.class);
    }
}
