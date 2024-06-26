package karuna.karuna_backend.content.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import karuna.karuna_backend.content.domain.ContentService;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import karuna.karuna_backend.content.dto.MassContentWrapperRequest;
import karuna.karuna_backend.exception.dto.DataIntegrityErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Updated contents",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MassContentWrapper.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data integrity error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DataIntegrityErrorResponse.class)))
    })
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    MassContentWrapper massUpdateContent(@RequestBody MassContentWrapperRequest massContentWrapperRequest) {
        return contentService.massUpdateContent(massContentWrapperRequest);
    }

    @Operation(summary = "Mass add content", description = "Getting json (REQUEST BODY) and adding all content by list")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Added contents",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MassContentWrapper.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data integrity error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DataIntegrityErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    MassContentWrapper massAddContent(@RequestBody MassContentWrapperRequest massContentWrapperRequest) {
        return contentService.massAddContent(massContentWrapperRequest);
    }

}