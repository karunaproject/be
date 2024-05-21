package karuna.karuna_backend.receiver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
