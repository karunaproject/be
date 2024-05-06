package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import karuna.karuna_backend.user.domain.UserService;
import karuna.karuna_backend.user.dto.UserDTO;
import karuna.karuna_backend.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostDto createPost(PostCreateDto postCreateDto, Principal principal) {
        String username = principal.getName();
        UserDTO user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Can not found user: %s".formatted(username)));
        Post post = Post.builder()
                .body(postCreateDto.body())
                .author(user.username())
                .build();
        return PostMapper.toDto(postRepository.save(post));
    }
}
