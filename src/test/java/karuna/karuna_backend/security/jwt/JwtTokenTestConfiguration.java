package karuna.karuna_backend.security.jwt;

import karuna.karuna_backend.TestJwtConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JwtTokenTestConfiguration {

    static JwtTokenService jwtTokenService() {
        return new JwtTokenService(new TestJwtConfig());
    }
}
