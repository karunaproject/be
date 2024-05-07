package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private static final String BODY = "BODY";
    private static final String NAME = "NAME";

    private PostService postService = PostTestConfiguration.postService();

    @Test
    void shouldCreatePost() {
        //given
        PostCreateDto postCreateDto = new PostCreateDto(BODY);
        Principal principal = () -> NAME;
        //when
        PostDto postDto = postService.createPost(postCreateDto, principal);
        //then
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.creteadAt());
        assertEquals(BODY, postDto.body());
        assertEquals(NAME, postDto.author());
    }

}