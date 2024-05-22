package karuna.karuna_backend.visitor.message.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import karuna.karuna_backend.exception.dto.EmailErrorResponse;
import karuna.karuna_backend.exception.dto.ValidationErrorResponse;
import karuna.karuna_backend.visitor.message.domain.VisitorMessageService;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Visitor message content",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitorMessageDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Can not send email",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmailErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VisitorMessageDto sendMessage(@RequestBody @Valid VisitorMessageCreateDto visitorMessageCreateDto) {
        //TODO: NEW VALIDATION AFTER CONTACT WITH CLIENT
        return visitorMessageService.sendMessage(visitorMessageCreateDto);
    }


    @Operation(summary = "Get messages", description = "Returns a list of messages with pagination and limited body length")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List od messages",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitorMessageWrapper.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    VisitorMessageWrapper getMessages(@RequestBody VisitorMessageRequest visitorMessageRequest) {
        return visitorMessageService.getMessages(visitorMessageRequest);
    }
}
