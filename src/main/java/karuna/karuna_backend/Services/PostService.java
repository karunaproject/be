package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.PostCreateDto;
import karuna.karuna_backend.DTO.PostDto;
import karuna.karuna_backend.Errors.UserExceptions.UserNotFoundException;
import karuna.karuna_backend.Mappers.PostMapper;
import karuna.karuna_backend.Models.Post;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.PostRepository;
import karuna.karuna_backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto createPost(PostCreateDto postCreateDto, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Can not found user: %s".formatted(username)));
        Post post = Post.builder()
                .body(postCreateDto.body())
                .user(user)
                .build();
        return PostMapper.toDto(postRepository.save(post));
    }
}
