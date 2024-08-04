package karuna.karuna_backend.user.domain

class UserConfiguration {

    private static UserRepository userRepository = new MockUserRepository()

    static UserService userService() {
        new UserService(new MockAuthenticationManager(), new MockJwtTokenService(), userRepository, new MockRoleRepository(), new MockPasswordEncoder())
    }

    static void cleanDatabase() {
        userRepository.deleteAll()
    }
}
