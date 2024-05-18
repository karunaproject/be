package karuna.karuna_backend.post.dto;

import java.time.OffsetDateTime;

public record PostDto(
        long id,
        OffsetDateTime createdAt,
        String body,
        String author
) {
}
