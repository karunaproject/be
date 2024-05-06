package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.post.dto.PostDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PostMapper {

    static PostDto toDto(Post post) {
        return new PostDto(post.getID(), post.getCreatedAt(), post.getBody(), post.getAuthor());
    }
}
