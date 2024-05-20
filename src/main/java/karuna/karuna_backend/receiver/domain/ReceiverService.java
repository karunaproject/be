package karuna.karuna_backend.receiver.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import karuna.karuna_backend.receiver.dto.ReceiversDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReceiverService {

    private  final ReceiverRepository receiverRepository;

    private final LoadingCache<String, ReceiversDTO> cache= Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build(this::getAllReceiversFromDatabase);

    public ReceiversDTO getAllReceivers() {
        return cache.get("allReceivers");
    }

    public ReceiverDTO addReceiver(String email) {
        ReceiverDTO receiverDTO;
        if(!cache.get("allReceivers").receivers().contains(email)){
            receiverDTO = ReceiverMapper.mapToDto(receiverRepository.save(Receiver.builder().email(email).build()));
            cache.get("allReceivers").receivers().add(receiverDTO.email());
        }else {
            receiverDTO = receiverRepository.findByEmailIgnoreCase(email);
        }
        return receiverDTO;
    }

    @Transactional
    public ResponseEntity<?> deleteReceiver(String email) {
        if(cache.get("allReceivers").receivers().contains(email)){
            receiverRepository.deleteByEmailIgnoreCase(email);
            cache.invalidate("allReceivers");
        }else {
            return new ResponseEntity<>("Recipient not found.", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("Recipient removed successfully.");
    }

    private ReceiversDTO getAllReceiversFromDatabase(String allReceivers) {
        return ReceiverMapper.mapToDto(receiverRepository.findAll(Sort.by(Sort.Direction.ASC, "email")));
    }
}
