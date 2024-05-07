package karuna.karuna_backend.exception.keys;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines JWT error types. " +
        "TOKEN_HAS_EXPIRED: The token has expired and is no longer valid. " +
        "TOKEN_VERIFICATION_FAILURE: Token verification failed due to a mismatch or corruption. " +
        "UNKNOWN_ERROR: An unknown error occurred during the token processing.")
public enum JwtErrorKey {


    TOKEN_HAS_EXPIRED,
    TOKEN_VERIFICATION_FAILURE,
    UNKNOWN_ERROR
}
