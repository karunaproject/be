package karuna.karuna_backend.DTO.VisitorMessage;

import java.time.OffsetDateTime;

public record VisitorMessageDto(
        long id,
        OffsetDateTime createdAt,
        String body,
        String contact
) {
}
