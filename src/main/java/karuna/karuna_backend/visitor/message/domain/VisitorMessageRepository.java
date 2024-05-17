package karuna.karuna_backend.visitor.message.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VisitorMessageRepository extends JpaRepository<VisitorMessage, Long> { }
