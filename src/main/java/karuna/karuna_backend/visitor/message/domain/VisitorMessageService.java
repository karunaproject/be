package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest;
import lombok.RequiredArgsConstructor;
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

    public List<VisitorMessageDto> getMessages(VisitorMessageRequest visitorMessageRequest) {
        List<VisitorMessage> messages = visitorMessageRepository.findAllByOrderByCreatedAtDesc();
        messages.forEach(message -> {
            if (message.getBody().length() > visitorMessageRequest.bodyLenLimit()) {
                message.setBody(message.getBody().substring(0, visitorMessageRequest.bodyLenLimit()));
            }
        });
        List<VisitorMessageDto> messagedtos = messages.stream()
                .map(VisitorMessageMapper::toDto).toList();

        return messagedtos;
    }
}
