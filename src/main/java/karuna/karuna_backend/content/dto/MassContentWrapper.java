package karuna.karuna_backend.content.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MassContentWrapper(
        @Schema(description = "List of valid content DTOs that passed validation and were processed successfully.")
        List<MassContentDto> validContents,

        @Schema(description = "List of invalid content DTOs that failed validation and were not processed.")
        List<MassContentDto> invalidContents)
{ }
