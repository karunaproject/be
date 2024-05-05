package karuna.karuna_backend.DTO.Post;

import java.time.OffsetDateTime;

public record PostDto(
        long id,
        OffsetDateTime creteadAt,
        String body,
        String author
) {
}
