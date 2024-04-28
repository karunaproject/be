package karuna.karuna_backend.Repositories;

import karuna.karuna_backend.Models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    public List<Content> findByPageIgnoreCase(String page);
    public List<Content> findByPageIsNull();
}
