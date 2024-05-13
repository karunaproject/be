package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import org.junit.jupiter.api.Test;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private PostService postService = PostTestConfiguration.postService();

    @Test
    void shouldCreatePost() {
        //given
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        Principal principal = () -> Constants.NAME;
        //when
        PostDto postDto = postService.createPost(postCreateDto, principal);
        //then
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.createdAt());
        assertEquals(Constants.BODY, postDto.body());
        assertEquals(Constants.NAME, postDto.author());
    }

}