package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.post.dto.PostDto;
import karuna.karuna_backend.post.dto.PostWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PostMapper {

    static PostDto toDto(Post post) {
        return new PostDto(post.getID(), post.getCreatedAt(), post.getBody(), post.getAuthor());
    }

    static PostWrapper toWrapper(Page<Post> posts) {
        return new PostWrapper(posts.map(PostMapper::toDto));
    }
}
