package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageCreateDto;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageDto;
import karuna.karuna_backend.Mappers.VisitorMessageMapper;
import karuna.karuna_backend.Models.VisitorMessage;
import karuna.karuna_backend.Repositories.VisitorMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorMessageService {

    private final VisitorMessageRepository visitorMessageRepository;

    public VisitorMessageDto sendMessage(VisitorMessageCreateDto visitorMessageCreateDto) {
        VisitorMessage visitorMessage = VisitorMessageMapper.toModel(visitorMessageCreateDto);
        return VisitorMessageMapper.toDto(visitorMessageRepository.save(visitorMessage));
    }
}
