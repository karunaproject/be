package karuna.karuna_backend.visitor.message.dto;

public record VisitorMessageRequest(int offset,
                                    int limit,
                                    int bodyLenLimit) {
}
