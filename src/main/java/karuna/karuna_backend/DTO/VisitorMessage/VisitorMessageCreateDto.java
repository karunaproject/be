package karuna.karuna_backend.DTO.VisitorMessage;


import jakarta.validation.constraints.Size;

public record VisitorMessageCreateDto(
        @Size(max = 1024)
        String body,
        @Size(max = 255)
        String contact
) {
}
