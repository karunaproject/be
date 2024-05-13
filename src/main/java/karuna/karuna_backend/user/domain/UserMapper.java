package karuna.karuna_backend.user.domain;

import karuna.karuna_backend.security.CustomUserDetails;
import karuna.karuna_backend.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserMapper {

    static UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getName).toList());
    }

    static CustomUserDetails toUserDetails(User user){
        return new CustomUserDetails(UserMapper.toDto(user));
    }
}
