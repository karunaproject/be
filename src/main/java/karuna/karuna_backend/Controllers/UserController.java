package karuna.karuna_backend.Controllers;

import karuna.karuna_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    /* Example of how to use swagger documentation
    TODO: Remove this example after implementing WhoAmI and Register routes
    @GetMapping("/user/{id}")
    @Operation(summary = "Get a user by ID", description = "Fetches a user by ID if it exists; otherwise, returns not found.")
    @ApiResponse(
            responseCode = "200",
            description = "Found the user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "User not found",
            content = {@Content(mediaType = "plain/text")})
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user and returns the created user object."
    )
    @ApiResponse(
            responseCode = "200",
            description = "User registered successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
    )
    public ResponseEntity<UserDTO> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User to register",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            )
            User user
    ){

        UserDTO createdUser = service.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

     */

}
