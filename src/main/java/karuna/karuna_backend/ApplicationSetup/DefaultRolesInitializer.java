package karuna.karuna_backend.ApplicationSetup;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Repositories.RoleRepository;

/**
 * Component responsible for initializing default roles in the database.
 * Implements {@link CommandLineRunner} to run this initialization code at application startup.
 */
@Component
@AllArgsConstructor
public class DefaultRolesInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    /**
     * Callback used to run the bean. It checks and creates default roles that are necessary for application operation.
     * This method is invoked at application startup.
     *
     * @param args command line arguments passed to the application, not used in this method.
     */
    @Override
    @Transactional
    public void run(String... args) {
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
    }

    /**
     * Ensures that a given role exists in the database. If the role does not exist, it creates and saves it.
     *
     * @param roleName the name of the role to check and potentially create.
     */
    private void createRoleIfNotFound(String roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));
    }
}
