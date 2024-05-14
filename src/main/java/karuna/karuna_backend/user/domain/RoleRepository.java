package karuna.karuna_backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
