package karuna.karuna_backend.visitor.message.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorMessageRepository extends JpaRepository<VisitorMessage, Long> {
    List<VisitorMessage> findAllByOrderByDateDesc();
}
