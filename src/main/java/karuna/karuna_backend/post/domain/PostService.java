package karuna.karuna_backend.post.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import karuna.karuna_backend.post.dto.PostWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private final LoadingCache<Pageable, PostWrapper> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build(this::getPostsFromDatabase);


    public PostWrapper getPosts(Pageable pageable) {
        return cache.get(pageable);
    }


    public PostWrapper getPostsFromDatabase(Pageable pageable) {
        List<Post> posts = postRepository.findAll(pageable).getContent();
        return PostMapper.toWrapper(posts);
    }
}
