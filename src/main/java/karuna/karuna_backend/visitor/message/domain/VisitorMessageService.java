package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.email.EmailSender;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitorMessageService {

    private final VisitorMessageRepository visitorMessageRepository;
    private final EmailSender emailSender;

    public VisitorMessageDto sendMessage(VisitorMessageCreateDto visitorMessageCreateDto) {
        VisitorMessage visitorMessage = VisitorMessageMapper.toModel(visitorMessageCreateDto);
        VisitorMessage savedVisitorMessage = visitorMessageRepository.save(visitorMessage);
        emailSender.sendEmail("HARDCODED-EMAIL@EMAIL.PL", "KARUN VISTOR MESSAGE", savedVisitorMessage.getBody(), null);
        return VisitorMessageMapper.toDto(savedVisitorMessage);
    }


    public VisitorMessageWrapper getMessages(VisitorMessageRequest visitorMessageRequest) {
        List<VisitorMessage> messages = visitorMessageRepository.findAll(visitorMessageRequest.pageRequest()).getContent();
        messages.forEach(message -> {
            if (message.getBody().length() > visitorMessageRequest.bodyLenLimit()) {
                message.setBody(message.getBody().substring(0, visitorMessageRequest.bodyLenLimit()));
            }
        });
        return VisitorMessageMapper.toWrapper(messages);
    }
}
