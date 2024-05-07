package karuna.karuna_backend.security.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JwtTokenTestConfiguration {

    static JwtTokenService jwtTokenService() {
        return new JwtTokenService(new TestJwtConfig());
    }
}
