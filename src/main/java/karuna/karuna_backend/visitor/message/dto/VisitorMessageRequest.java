package karuna.karuna_backend.visitor.message.dto;

import org.springframework.data.domain.PageRequest;

public record VisitorMessageRequest(int offset,
                                    int limit,
                                    int bodyLenLimit) {
    public PageRequest toPageRequest() {
        return PageRequest.of(offset, limit);
    }
}