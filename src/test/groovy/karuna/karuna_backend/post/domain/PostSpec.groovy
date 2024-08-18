package karuna.karuna_backend.post.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.MockAuthentication
import karuna.karuna_backend.post.dto.PostCreateDto
import karuna.karuna_backend.post.dto.PostDto
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

class PostSpec extends Specification implements Constants {

    private PostService postService = PostConfiguration.postService()

    def "should create post" () {
        when: "Set authentiactiond and create post with body $BODY and author $USERNAME"
            PostCreateDto request = new PostCreateDto(BODY)
            SecurityContextHolder.getContext().setAuthentication(new MockAuthentication())
            PostDto response = postService.createPost(request)
        then: "Check if correct create post"
            response.author() == USERNAME
            response.body() == BODY
    }
}
