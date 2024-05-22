package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class VisitorMessageMapper {

    static VisitorMessage toModel(VisitorMessageCreateDto visitorMessageCreateDto) {
        return VisitorMessage.builder()
                .body(visitorMessageCreateDto.body())
                .contact(visitorMessageCreateDto.contact())
                .build();
    }

    static VisitorMessageDto toDto(VisitorMessage visitorMessage) {
        return new VisitorMessageDto(visitorMessage.getID(), visitorMessage.getCreatedAt(), visitorMessage.getBody(), visitorMessage.getContact());
    }

    static VisitorMessageWrapper toWrapper(List<VisitorMessage> messages) {
        return new VisitorMessageWrapper(messages.stream()
                .map(VisitorMessageMapper::toDto)
                .toList());
    }

}
