package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.email.EmailSender;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
