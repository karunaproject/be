package karuna.karuna_backend.ApplicationSetup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Repositories.RoleRepository;

@Component
public class DefaultRolesInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DefaultRolesInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
    }

    private void createRoleIfNotFound(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}
