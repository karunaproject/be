package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.email.EmailSender;
import karuna.karuna_backend.receiver.domain.ReceiverService;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VisitorMessageService {

    private final VisitorMessageRepository visitorMessageRepository;
    private final EmailSender emailSender;
    private final ReceiverService receiverService;

    public VisitorMessageDto sendMessage(VisitorMessageCreateDto visitorMessageCreateDto) {
        VisitorMessage visitorMessage = VisitorMessageMapper.toModel(visitorMessageCreateDto);
        VisitorMessage savedVisitorMessage = visitorMessageRepository.save(visitorMessage);
        JSONArray recipients = new JSONArray();
        for (String email : receiverService.getAllReceivers().receivers()) {
            recipients.put(new JSONObject().put("Email", email).put("Name", email));
        }
        emailSender.sendEmails(recipients, "KARUN VISTOR MESSAGE", savedVisitorMessage.getBody(), null);
        return VisitorMessageMapper.toDto(savedVisitorMessage);
    }

}
