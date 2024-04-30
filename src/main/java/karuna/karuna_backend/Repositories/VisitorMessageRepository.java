package karuna.karuna_backend.Repositories;

import karuna.karuna_backend.Models.VisitorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorMessageRepository extends JpaRepository<VisitorMessage, Long> { }
