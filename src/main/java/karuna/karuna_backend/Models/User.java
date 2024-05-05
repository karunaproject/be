package karuna.karuna_backend.Models;


import jakarta.persistence.*;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.DTO.UserDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    public UserDTO dto(){
        return UserDTO.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }

    //TODO: Refactor into separate class
    public CustomUserDetails mapToUserDetails(){
        return new CustomUserDetails(this);
    }

}
