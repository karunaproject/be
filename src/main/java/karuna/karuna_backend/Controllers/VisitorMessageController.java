package karuna.karuna_backend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageCreateDto;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageDto;
import karuna.karuna_backend.Services.VisitorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitors/messages")
@RequiredArgsConstructor
class VisitorMessageController {

    private final VisitorMessageService visitorMessageService;

    @Operation(summary = "Create visitor message", description = "Getting body from json (body of request) and saving it to database as visitor message with current date")
    @ApiResponse(
            responseCode = "201",
            description = "Visitor message content",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitorMessageDto.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VisitorMessageDto sendMessage(@RequestBody @Valid VisitorMessageCreateDto visitorMessageCreateDto) {
        return visitorMessageService.sendMessage(visitorMessageCreateDto);
    }
}
