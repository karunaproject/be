package karuna.karuna_backend.receiver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "receivers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Invalid email address")
    @Size(min = 5, max = 320)
    private String email;

}
