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
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_ADMIN");
            roleRepository.save(userRole);
        }
    }
}
