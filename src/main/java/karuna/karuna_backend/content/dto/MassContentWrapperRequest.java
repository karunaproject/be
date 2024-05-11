package karuna.karuna_backend.content.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MassContentWrapperRequest(
        @Schema(description ="List of contents to perform operation on")
        List<MassContentDto> contents) {
}
