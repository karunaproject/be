package karuna.karuna_backend.Mappers;

import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageCreateDto;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageDto;
import karuna.karuna_backend.Models.VisitorMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorMessageMapper {

    public static VisitorMessage toModel(VisitorMessageCreateDto visitorMessageCreateDto) {
        return VisitorMessage.builder()
                .body(visitorMessageCreateDto.body())
                .contact(visitorMessageCreateDto.contact())
                .build();
    }

    public static VisitorMessageDto toDto(VisitorMessage visitorMessage) {
        return new VisitorMessageDto(visitorMessage.getID(), visitorMessage.getCreatedAt(), visitorMessage.getBody(), visitorMessage.getContact());
    }
}
