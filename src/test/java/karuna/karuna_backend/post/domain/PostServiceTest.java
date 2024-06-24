package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import karuna.karuna_backend.post.dto.PostWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostServiceTest {

    private final PostService postService = PostTestConfiguration.postService();

    @Test
    void shouldCreatePost() {
        //when: When create post, returns created post
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        Principal principal = () -> Constants.NAME;
        PostDto postDto = postService.createPost(postCreateDto);
        //then: Check if  database give id and correctly save data
        assertEquals(1L, postDto.id());
        assertNotNull(postDto.createdAt());
        assertEquals(Constants.BODY, postDto.body());
    }

    @Test
    void shouldGetPosts() {
        //given
        PostCreateDto postCreateDto = new PostCreateDto(Constants.BODY);
        PostDto postDto1 = postService.createPost(postCreateDto);
        PostDto postDto2 = postService.createPost(postCreateDto);
        PostDto postDto3 = postService.createPost(postCreateDto);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        //when
        PostWrapper posts = postService.getPosts(pageable);
        List<PostDto> listPost = posts.posts();
        //then
        assertNotNull(listPost);
        assertEquals(3, listPost.size());
        assertEquals("Body3", listPost.get(0).body());
        assertEquals("Body2", listPost.get(1).body());
        assertEquals("Body3", listPost.get(2).body());
    }

}