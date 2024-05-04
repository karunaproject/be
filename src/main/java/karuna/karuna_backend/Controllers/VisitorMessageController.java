package karuna.karuna_backend.Controllers;

import jakarta.validation.Valid;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageCreateDto;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageDto;
import karuna.karuna_backend.Services.VisitorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitors/messages")
@RequiredArgsConstructor
class VisitorMessageController {

    private final VisitorMessageService visitorMessageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VisitorMessageDto sendMessage(@RequestBody @Valid VisitorMessageCreateDto visitorMessageCreateDto) {
        return visitorMessageService.sendMessage(visitorMessageCreateDto);
    }
}
