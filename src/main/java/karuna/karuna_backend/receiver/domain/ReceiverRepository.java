package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReceiverRepository extends JpaRepository<Receiver, Integer> {

    ReceiverDTO findByEmailIgnoreCase(String email);

    void deleteByEmailIgnoreCase(String email);
}
