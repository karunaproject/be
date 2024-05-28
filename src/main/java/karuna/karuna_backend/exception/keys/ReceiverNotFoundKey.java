package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines receiver error types. " +
        "NOT_FOUND: Recipient not found in database.")
public enum ReceiverNotFoundKey {
    NOT_FOUND
}
