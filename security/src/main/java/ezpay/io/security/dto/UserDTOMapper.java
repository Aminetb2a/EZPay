package ezpay.io.security.dto;

import org.springframework.stereotype.Component;
import ezpay.io.security.entity.User;

@Component
public class UserDTOMapper {
    
    public UserDto mapToUserDTO(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getName(), null);
    }
    
    public User mapToUser(UserDto user) {
        return new User(user.getId(), user.getEmail(), user.getName(), user.getPassword());
    }
}
