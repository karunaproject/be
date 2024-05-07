package karuna.karuna_backend.security.jwt;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

class TestJwtConfig extends JwtConfig{

    private static final String SECRET_KEY = "sGkXqJf3chLX7T52V7kPOj+KSLPZBEyeU9HxniZb2Sw";
    private static final String TOKEN_PREFIX = "TOKEN_PREFIX";
    private static final Duration TOKEN_EXPIRATION_TIME = Duration.ofSeconds(1);

    TestJwtConfig() {
        super.setSecretKey(SECRET_KEY);
        super.setTokenPrefix(TOKEN_PREFIX);
        super.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
    }
}
