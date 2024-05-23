package karuna.karuna_backend.receiver.infrastrucutre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import karuna.karuna_backend.exception.dto.JwtErrorResponse;
import karuna.karuna_backend.exception.dto.ValidationErrorResponse;
import karuna.karuna_backend.receiver.domain.ReceiverService;
import karuna.karuna_backend.receiver.dto.ReceiverCreateDto;
import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import karuna.karuna_backend.receiver.dto.ReceiversDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receivers")
class ReceiverController {

    private final ReceiverService receiverService;

    @GetMapping
    @Operation(summary = "Get all recipients of visitor message", description = "Retrieves all recipients from database.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All recipients from database",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceiversDTO.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authorization failed",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtErrorResponse.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    ReceiversDTO getAllReceivers() {
        return receiverService.getAllReceivers();
    }

    @PostMapping
    @Operation(summary = "Add recipient to database.", description = "Create new recipient in database.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipient added successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceiverDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect email",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authorization failed",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtErrorResponse.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    ReceiverDTO addReceiver(@RequestBody @Valid ReceiverCreateDto email) {
        return receiverService.addReceiver(email.email());
    }

    @DeleteMapping
    @Operation(summary = "Remove recipient from database.", description = "Remove recipient from database.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipient removed successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recipient not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: You don't have permission to access this resource"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authorization failed",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtErrorResponse.class)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteReceiver(@RequestBody ReceiverCreateDto email) {
        return receiverService.deleteReceiver(email.email());
    }
}
