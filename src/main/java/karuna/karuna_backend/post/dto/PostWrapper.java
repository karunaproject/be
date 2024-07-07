package karuna.karuna_backend.post.dto;

import org.springframework.data.domain.Page;

public record PostWrapper(Page<PostDto> posts) {
}