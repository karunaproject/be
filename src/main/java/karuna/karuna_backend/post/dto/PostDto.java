package karuna.karuna_backend.post.dto;

import java.time.OffsetDateTime;

public record PostDto(
        long id,
        OffsetDateTime creteadAt,
        String body,
        String author
) {
}
