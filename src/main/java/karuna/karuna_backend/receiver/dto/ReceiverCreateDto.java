package karuna.karuna_backend.receiver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ReceiverCreateDto(
        @Email(message = "Invalid email address")
        @Size(min = 5, max = 255)
        String email) {
}
