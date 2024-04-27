package karuna.karuna_backend.Repositories;

import karuna.karuna_backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findById(int id);
    public Optional<User> findByUsername(String username);
}
