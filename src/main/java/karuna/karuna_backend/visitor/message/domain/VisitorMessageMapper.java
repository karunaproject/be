package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    static VisitorMessage shortenMessageBody(VisitorMessage message, int bodyLenLimit) {
        if (message.getBody().length() > bodyLenLimit) {
            message.setBody(message.getBody().substring(0, bodyLenLimit));
        }
        return message;
    }
}
