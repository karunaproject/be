package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.PostCreateDto;
import karuna.karuna_backend.DTO.PostDto;
import karuna.karuna_backend.Errors.UserExceptions.UserNotFoundException;
import karuna.karuna_backend.Models.Post;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.PostRepository;
import karuna.karuna_backend.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    private static final String BODY = "BODY";
    private static final String PRINCIPAL_NAME = "PRINCIPAL_NAME";

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void shouldCreatePost() {
        //given
        PostCreateDto postCreateDto = new PostCreateDto(BODY);
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return PRINCIPAL_NAME;
            }
        };
        User user = User.builder()
                .username(PRINCIPAL_NAME)
                .build();
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Post post = Post.builder()
                .user(user)
                .body(BODY)
                .createdAt(offsetDateTime)
                .ID(1L)
                .build();
        //when
        when(userRepository.findByUsername(PRINCIPAL_NAME)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDto postDto = postService.createPost(postCreateDto, principal);

        //then

        assertEquals(1L, postDto.id());
        assertEquals(BODY, postDto.body());
        assertEquals(PRINCIPAL_NAME, postDto.author());
        assertEquals(offsetDateTime, postDto.creteadAt());
    }

    @Test
    void shouldThrowUserNotFoundOnCreatePost() {
        //given
        PostCreateDto postCreateDto = new PostCreateDto(BODY);
        Principal principal = () -> PRINCIPAL_NAME;
        //when
        when(userRepository.findByUsername(PRINCIPAL_NAME)).thenThrow(UserNotFoundException.class);

        //then
        assertThrows(UserNotFoundException.class, () -> postService.createPost(postCreateDto, principal));
    }
}