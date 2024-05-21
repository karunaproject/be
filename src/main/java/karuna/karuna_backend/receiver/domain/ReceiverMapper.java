package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import karuna.karuna_backend.receiver.dto.ReceiversDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ReceiverMapper {

    static ReceiversDTO mapToDto(List<Receiver> receivers) {
        return new ReceiversDTO(receivers.stream().map(Receiver::getEmail).collect(Collectors.toSet()));
    }

    static ReceiverDTO mapToDto(Receiver receiver) {
        return new ReceiverDTO(receiver.getId(), receiver.getEmail());
    }
}
