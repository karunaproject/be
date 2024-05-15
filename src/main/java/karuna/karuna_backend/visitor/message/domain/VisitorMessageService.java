package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDtoList;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitorMessageService {

    private final VisitorMessageRepository visitorMessageRepository;

    public VisitorMessageDto sendMessage(VisitorMessageCreateDto visitorMessageCreateDto) {
        VisitorMessage visitorMessage = VisitorMessageMapper.toModel(visitorMessageCreateDto);
        return VisitorMessageMapper.toDto(visitorMessageRepository.save(visitorMessage));
    }


    public VisitorMessageDtoList getMessages(VisitorMessageRequest visitorMessageRequest) {
        PageRequest pageRequest = PageRequest.of(visitorMessageRequest.offset(), visitorMessageRequest.limit());
        List<VisitorMessage> messages = visitorMessageRepository.findAllByOrderByCreatedAtDesc(pageRequest);
        messages.forEach(message -> {
            if (message.getBody().length() > visitorMessageRequest.bodyLenLimit()) {
                message.setBody(message.getBody().substring(0, visitorMessageRequest.bodyLenLimit()));
            }
        });
        List<VisitorMessageDto> messagesDto = messages.stream()
                .map(VisitorMessageMapper::toDto).toList();
        return new VisitorMessageDtoList(messagesDto);
    }
}
