package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import karuna.karuna_backend.utils.AuthenticationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private final PostService postService = PostTestConfiguration.postService();
    private Authentication authentication;

    @Test
    void shouldCreatePost() {
        // given: Set up authentication context for the test user
        authentication = new TestingAuthenticationToken(
                Constants.USERNAME,
                null,
                Constants.ROLE_USER);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //when: When create a new post
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        PostDto postDto = postService.createPost(postCreateDto);
        String username = AuthenticationUtil.getUsername();
        //then: Check if database give ID and correctly save post
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.createdAt());
        assertEquals(Constants.BODY, postDto.body());
        assertEquals(username, postDto.author());
        SecurityContextHolder.clearContext();
    }

}