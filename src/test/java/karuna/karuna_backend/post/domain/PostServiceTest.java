package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private final PostService postService = PostTestConfiguration.postService();

    @Test
    void shouldCreatePost() {
        //when: When create post, returns created post
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        Principal principal = () -> Constants.NAME;
        PostDto postDto = postService.createPost(postCreateDto, principal);
        //then: Check if  database give id and correctly save data
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.creteadAt());
        assertEquals(Constants.BODY, postDto.body());
        assertEquals(Constants.NAME, postDto.author());
    }

}