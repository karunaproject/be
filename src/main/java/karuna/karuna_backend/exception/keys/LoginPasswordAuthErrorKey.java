package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines error types related to login and password authentication. " +
        "WRONG_LOGIN_CREDENTIALS: Credentials provided by the user are invalid.")
public enum LoginPasswordAuthErrorKey {
        WRONG_LOGIN_CREDENTIALS
}
