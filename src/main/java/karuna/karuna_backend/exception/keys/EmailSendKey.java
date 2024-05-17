package karuna.karuna_backend.exception.keys;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines emails send error types. " +
        "CAN_NOT_SEND: Error while sending email. Probably problem with provider! ")
public enum EmailSendKey {
    CAN_NOT_SEND
}
