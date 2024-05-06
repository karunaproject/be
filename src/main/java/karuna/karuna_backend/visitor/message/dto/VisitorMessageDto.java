package karuna.karuna_backend.visitor.message.dto;

import java.time.OffsetDateTime;

public record VisitorMessageDto(
        long id,
        OffsetDateTime createdAt,
        String body,
        String contact
) {
}
