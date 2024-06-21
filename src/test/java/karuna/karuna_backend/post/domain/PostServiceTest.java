package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private final PostService postService = PostTestConfiguration.postService();

    @Test
    void shouldCreatePost() {
        //when: When create post, returns created post
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        PostDto postDto = postService.createPost(postCreateDto);
        //then: Check if  database give id and correctly save data
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.createdAt());
        assertEquals(Constants.BODY, postDto.body());
    }

}