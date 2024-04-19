package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.UserDTO;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository repository;

    //@Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a user by their ID.
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the UserDTO if the user is found, or an empty Optional if not found.
     */
    public Optional<UserDTO> getUserById(int id){
        Optional<User> optionalUser = repository.findByID(id);
        return optionalUser.map(this::convertToDTO);

    }

    /**
     * Registers a new user in the repository.
     * This method saves the provided User object and returns a UserDTO representation of the saved user.
     * @param user the User to register.
     * @return the UserDTO representation of the registered user.
     */
    public UserDTO registerUser(User user){
        return convertToDTO(repository.save(user));
    }


    /**
     * Converts a User entity to a UserDTO.
     * This method is used internally to transform User objects into their Data Transfer Object (DTO) form.
     * @param user the User to convert.
     * @return the converted UserDTO.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
