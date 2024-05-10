package karuna.karuna_backend.content.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByPageIgnoreCaseOrPageNull(String page);
    Optional<Content> findByPageAndKey(String page, String key);
}
