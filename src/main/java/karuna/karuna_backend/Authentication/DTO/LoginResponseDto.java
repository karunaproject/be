package karuna.karuna_backend.Authentication.DTO;

import lombok.Builder;
import java.time.OffsetDateTime;

@Builder
public record LoginResponseDto(String username, OffsetDateTime tokenExpirationTime){}