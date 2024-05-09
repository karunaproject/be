package karuna.karuna_backend.content.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import karuna.karuna_backend.content.domain.ContentService;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
class ContentController {

    private final ContentService contentService;

    @Operation(summary = "Get content by page", description = "Fetches content by page name, even if page doesn't " +
            "exist it will always return default content")
    @ApiResponse(
            responseCode = "200",
            description = "Content for page",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContentDTO.class)))
    @GetMapping("/{page}")
    ContentDTO getContentByPage(@PathVariable String page) {
        return contentService.getContentByPage(page);
    }

    @Operation(summary = "Mass update content", description = "Getting json (REQUEST BODY) and updating all content by list")
    @ApiResponse(
            responseCode = "200",
            description = "Updated contents",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MassContentWrapper.class)))
    @PutMapping("/specific-path")
    MassContentWrapper massUpdateContent(@RequestBody MassContentWrapper massContentWrapper) {
        return contentService.massUpdateContent(massContentWrapper);
    }

}