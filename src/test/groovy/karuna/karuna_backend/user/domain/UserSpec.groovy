package karuna.karuna_backend.user.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.user.dto.UserDTO
import spock.lang.Specification

class UserSpec extends Specification implements Constants {

    private UserService userService = UserConfiguration.userService()

    def setup() {
        UserConfiguration.cleanDatabase()
    }

    def "should not get user by id" () {
        when: "Get not existed user"
            Optional<UserDTO> response = userService.getUserById(1L)
        then: "Check if user not found"
            response.isEmpty()
    }

    def "should het user by id" () {
        given: "Register new user"
            userService.registerUser(USERNAME, USERNAME)
        when: "Get registered user by id"
            Optional<UserDTO> response = userService.getUserById(1L)
        then:
            response.isPresent()
            UserDTO userDTO = response.get()
            userDTO.username() == USERNAME
            userDTO.password() == USERNAME
            !userDTO.roles().isEmpty()
            userDTO.id() == 1L
    }

    def "should not get user by username" () {
        when: "Get not existed user"
            Optional<UserDTO> response = userService.getUserByUsername(USERNAME)
        then: "Check if user not found"
            response.isEmpty()
    }

    def "should het user by username" () {
        given: "Register new user"
        userService.registerUser(USERNAME, USERNAME)
        when: "Get registered user by username"
        Optional<UserDTO> response = userService.getUserByUsername(USERNAME)
        then:
        response.isPresent()
        UserDTO userDTO = response.get()
        userDTO.username() == USERNAME
        userDTO.password() == USERNAME
        !userDTO.roles().isEmpty()
        userDTO.id() == 1L
    }

    def "should register new user" () {
        when: "Register new user"
            String token = userService.registerUser(USERNAME, USERNAME)
        then: "Check generated token"
            token == TOKEN + USERNAME
    }

    def "should authenticate user with mock authenticator" () {
        when: "Authenticate mock user"
            String response = userService.authenticateUser(USERNAME, USERNAME)
        then:
            response == TOKEN + USERNAME
    }
}
