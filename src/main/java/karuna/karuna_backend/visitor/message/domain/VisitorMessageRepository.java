package karuna.karuna_backend.visitor.message.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorMessageRepository extends JpaRepository<VisitorMessage, Long> {
    List<VisitorMessage> findAllByOrderByDateDesc();
}
