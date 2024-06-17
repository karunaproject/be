package karuna.karuna_backend.visitor.message.dto;

import org.springframework.data.domain.PageRequest;

public record VisitorMessageRequest(PageRequest pageRequest,
                                    int bodyLenLimit) {

}