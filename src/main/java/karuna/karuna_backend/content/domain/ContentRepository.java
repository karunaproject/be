package karuna.karuna_backend.content.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByPageIgnoreCaseOrPageNull(String page);
}
