package karuna.karuna_backend.post.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PostTestConfiguration {

    static PostService postService() {
        return new PostService(new InMemoryPostRepository());
    }
}
