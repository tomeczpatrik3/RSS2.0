package RoomReservationSystem.dto;

import RoomReservationSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String name;
    private String email;
    private String password;
    
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                "hehexd"
        );
    }
}
