package karuna.karuna_backend.Authentication.DTO;

import lombok.Builder;
import java.time.OffsetDateTime;

@Builder
public record RegisterResponseDto(String username, OffsetDateTime tokenExpirationTime){}