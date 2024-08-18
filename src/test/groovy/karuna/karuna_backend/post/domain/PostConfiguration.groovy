package karuna.karuna_backend.post.domain

class PostConfiguration {

    static PostService postService() {
        new PostService(new MockPostRepository())
    }
}
