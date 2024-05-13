package karuna.karuna_backend;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String PAGE = "PAGE";
    public static final String KEY = "KEY";
    public static final String VALUE_PL = "VALUE_PL";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE_USER = "ROLE_USER";

    public static final String BODY = "BODY";
    public static final String NAME = "NAME";
    public static final String CONTACT = "CONTACT";
    public static final String SECRET_KEY = "sGkXqJf3chLX7T52V7kPOj+KSLPZBEyeU9HxniZb2Sw";
    public static final String TOKEN_PREFIX = "TOKEN_PREFIX";
    public static final Duration TOKEN_EXPIRATION_TIME = Duration.ofSeconds(1);
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}
