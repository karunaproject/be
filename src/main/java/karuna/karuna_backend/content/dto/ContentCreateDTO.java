package karuna.karuna_backend.content.dto;

public record ContentCreateDTO(String key, String value) {

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
