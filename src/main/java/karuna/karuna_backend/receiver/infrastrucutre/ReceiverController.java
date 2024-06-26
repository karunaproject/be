package karuna.karuna_backend.receiver.infrastrucutre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import karuna.karuna_backend.exception.dto.ReceiverErrorResponse;
import karuna.karuna_backend.exception.dto.ValidationErrorResponse;
import karuna.karuna_backend.receiver.domain.ReceiverService;
import karuna.karuna_backend.receiver.dto.ReceiverRequestDto;
import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import karuna.karuna_backend.receiver.dto.ReceiversDTO;
import karuna.karuna_backend.receiver.annotations.ErrorsDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receivers")
public class ReceiverController {

    private final ReceiverService receiverService;

    @GetMapping
    @Operation(summary = "Get all recipients of visitor message", description = "Retrieves all recipients from database.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All recipients from database",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceiversDTO.class))),
    })
    @ErrorsDescription
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
    })
    @ErrorsDescription
    @PreAuthorize("hasRole('ADMIN')")
    ReceiverDTO addReceiver(@RequestBody @Valid ReceiverRequestDto receiver) {
        return receiverService.addReceiver(receiver);
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
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceiverErrorResponse.class))),
    })
    @ErrorsDescription
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteReceiver(@RequestBody ReceiverRequestDto receiver) {
        return receiverService.deleteReceiver(receiver);
    }
}
