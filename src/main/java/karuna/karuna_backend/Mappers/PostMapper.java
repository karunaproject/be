package karuna.karuna_backend.Mappers;

import karuna.karuna_backend.DTO.Post.PostDto;
import karuna.karuna_backend.Models.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {

    public static PostDto toDto(Post post) {
        return new PostDto(post.getID(), post.getCreatedAt(), post.getBody(), post.getUser().getUsername());
    }
}
