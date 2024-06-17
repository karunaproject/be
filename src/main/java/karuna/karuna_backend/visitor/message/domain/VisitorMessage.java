package karuna.karuna_backend.visitor.message.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "visitors_messages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
class VisitorMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "created_at")
    @NotNull
    private OffsetDateTime createdAt;

    @Column(columnDefinition = "TEXT", length = 1024)
    private String body;

    @Column(columnDefinition = "VARCHAR(255)")
    private String contact;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }
}
