package karuna.karuna_backend.Repositories;

import karuna.karuna_backend.Models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    public Optional<List<Content>> findByPage(String page);
    public List<Content> findByPageIsNull();
}
