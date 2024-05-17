package karuna.karuna_backend.content.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "contents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(unique = true)
    private String page;

    @NotNull
    @Column(unique = true)
    private String key;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String valuePl;

}
