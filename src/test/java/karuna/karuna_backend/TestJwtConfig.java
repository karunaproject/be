package karuna.karuna_backend;

import karuna.karuna_backend.security.jwt.JwtConfig;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class TestJwtConfig extends JwtConfig {



    public TestJwtConfig() {
        super.setSecretKey(Constants.SECRET_KEY);
        super.setTokenPrefix(Constants.TOKEN_PREFIX);
        super.setTokenExpirationTime(Constants.TOKEN_EXPIRATION_TIME);
    }
}
