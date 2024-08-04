package karuna.karuna_backend.user.domain

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import karuna.karuna_backend.Constants
import karuna.karuna_backend.security.CustomUserDetails
import karuna.karuna_backend.security.jwt.JwtTokenService

import java.time.OffsetDateTime

class MockJwtTokenService implements JwtTokenService, Constants {

    @Override
    String generateToken(CustomUserDetails user, Map<String, Object>... additionalClaims) {
        TOKEN + user.getUsername()
    }

    @Override
    void verifyToken(String token) throws SignatureException, ExpiredJwtException, MalformedJwtException {

    }

    @Override
    OffsetDateTime getExpirationDate(String token) {
        return null
    }

    @Override
    String getSubject(String token) {
        return null
    }

    @Override
    List<String> getRoles(String token) {
        return null
    }
}
