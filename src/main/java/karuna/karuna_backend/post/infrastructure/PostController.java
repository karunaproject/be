package karuna.karuna_backend.post.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import karuna.karuna_backend.post.domain.PostService;
import karuna.karuna_backend.post.dto.PostCreateDto;
import karuna.karuna_backend.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
class PostController {

    private final PostService postService;

    @Operation(summary = "Create post", description = "Getting body from json (body of request) and principal from token and saving it to database as post with current date")
    @ApiResponse(
            responseCode = "201",
            description = "Post content",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostDto createPost(@RequestBody PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto);
    }
}
