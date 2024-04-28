package karuna.karuna_backend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import karuna.karuna_backend.DTO.ContentDTO;
import karuna.karuna_backend.Services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @Operation(summary = "Get content by page", description = "Fetches content by page name, even if page doesn't " +
            "exist it will always return default content")
    @ApiResponse(
            responseCode = "200",
            description = "Content for page",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContentDTO.class)))
    @GetMapping("/{page}")
    public List<ContentDTO> getContentByPage(@PathVariable String page) {
        return contentService.getContentByPage(page);
    }

}