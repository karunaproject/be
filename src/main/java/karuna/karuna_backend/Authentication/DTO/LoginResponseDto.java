package karuna.karuna_backend.Authentication.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Date;

@Builder
public record LoginResponseDto(String username, OffsetDateTime tokenExpirationTime){}