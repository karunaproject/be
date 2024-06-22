package karuna.karuna_backend.post.domain;

import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static karuna.karuna_backend.utils.AuthenticationUtil.getUsername;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDto createPost(PostCreateDto postCreateDto) {
        String username = getUsername();
        Post post = Post.builder()
                .body(postCreateDto.body())
                .author(username)
                .build();
        return PostMapper.toDto(postRepository.save(post));
    }

    public List<PostDto> getPost(Pageable pageable) {
        List<Post> posts = postRepository.findAll(pageable).getContent();
        List<PostDto> postDtos = posts.stream().map(e -> PostMapper.toDto(e)).toList();
        return  postDtos;
    }
}
