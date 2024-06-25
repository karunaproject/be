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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        //given: Create the posts
        PostCreateDto postCreateDto1 = new PostCreateDto("Body1");
        PostCreateDto postCreateDto2 = new PostCreateDto("Body2");
        PostCreateDto postCreateDto3 = new PostCreateDto("Body3");
        PostDto postDto1 = postService.createPost(postCreateDto1);
        PostDto postDto2 = postService.createPost(postCreateDto2);
        PostDto postDto3 = postService.createPost(postCreateDto3);
        //and: Set up pagination and sorting
        Pageable pageable = PageRequest.of(0, 10, Sort.by("ID").descending());
        //when: Fetch the posts
        PostWrapper posts = postService.getPosts(pageable);
        List<PostDto> listPost = posts.posts();
        //then: Verify the results
        assertNotNull(listPost);
        assertEquals(3, listPost.size());
        assertEquals("Body3", listPost.get(0).body());
        assertEquals("Body2", listPost.get(1).body());
        assertEquals("Body1", listPost.get(2).body());
    }

}