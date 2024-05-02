package karuna.karuna_backend.Errors.ErrorKeys;
import io.swagger.v3.oas.annotations.media.Schema;

public enum JwtErrorKey {

    @Schema(description = "The token has expired and is no longer valid.")
    TOKEN_HAS_EXPIRED,

    @Schema(description = "Token verification failed due to a mismatch or corruption.")
    TOKEN_VERIFICATION_FAILURE,

    @Schema(description = "An unknown error occurred during the token processing.")
    UNKNOWN_ERROR
}
