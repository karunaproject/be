package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.UserDTO;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_Found() {
        // Mock data
        int userId = 1;
        String username = "test_user";
        String password = "test_password";
        User user = new User(userId, username, password);
        Optional<User> optionalUser = Optional.of(user);

        // Mock repository behavior
        when(userRepository.findByID(userId)).thenReturn(optionalUser);

        // Call the method
        Optional<UserDTO> userDTO = userService.getUserById(userId);

        // Assertions
        userDTO.ifPresent(foundUser -> assertEquals(username, foundUser.getUsername()));

        // Verify interactions with the repository
        verify(userRepository).findByID(userId);
    }

    @Test
    public void testGetUserById_NotFound() {
        // Mock data
        int userId = 1;

        // Mock repository behavior
        when(userRepository.findByID(userId)).thenReturn(Optional.empty());

        // Call the method
        Optional<UserDTO> userDTO = userService.getUserById(userId);

        // Assertions
        assertFalse(userDTO.isPresent());

        // Verify interactions with the repository
        verify(userRepository).findByID(userId);
    }

    @Test
    public void testRegisterUser() {
        // Mock data
        int userId = 1;
        String username = "test_user";
        String password = "test_password";
        User user = new User(userId, username, password);
        UserDTO userDTO = new UserDTO(username, password);

        // Mock repository behavior
        when(userRepository.save(user)).thenReturn(user);

        // Call the method
        UserDTO registeredUser = userService.registerUser(user);

        // Assertions
        assertEquals(userDTO.getUsername(), registeredUser.getUsername());

        // Verify interactions with the repository
        verify(userRepository).save(user);
    }
}