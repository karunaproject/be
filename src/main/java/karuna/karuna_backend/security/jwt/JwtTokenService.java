package karuna.karuna_backend.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import karuna.karuna_backend.security.CustomUserDetails;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public interface JwtTokenService {
    String generateToken(CustomUserDetails user, Map<String, Object>... additionalClaims);

    void verifyToken(String token) throws SignatureException, ExpiredJwtException, MalformedJwtException;

    OffsetDateTime getExpirationDate(String token);

    String getSubject(String token);

    List<String> getRoles(String token);
}
