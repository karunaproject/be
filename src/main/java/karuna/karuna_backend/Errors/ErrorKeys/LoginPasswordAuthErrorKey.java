package karuna.karuna_backend.Errors.ErrorKeys;

import io.swagger.v3.oas.annotations.media.Schema;

public enum LoginPasswordAuthErrorKey {

        @Schema(description = "Credentials provided by user are invalid")
        WRONG_LOGIN_CREDENTIALS
}
