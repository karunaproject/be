package karuna.karuna_backend.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "karuna.email")
@Getter
@Setter
@NoArgsConstructor
class EmailConfig {

    private String from;
}
