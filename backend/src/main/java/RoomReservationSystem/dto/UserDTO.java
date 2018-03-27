package RoomReservationSystem.dto;

import RoomReservationSystem.model.User;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A felhasználókhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;    /*A felhasználónév*/
    private String name;        /*A felhasználó neve*/
    private String email;       /*A felhasználó e-mail címe*/
    private String password;    /*A felhasználó jelszava*/
    
    /**
     * A User objektumból UserDTO objektum létrehozásáért felelős metódus
     * @param   user    A User obejktum
     * @return          A UserDTO objektum
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                "hehexd"
        );
    }
    
    /**
     * Több User objektum UserDTO objektummá alakításáért felelős metódus
     * @param   users   A User objektumok egy listában
     * @return          A UserDTO objektumok egy listában
     */
    public static List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach( (user) -> {
            userDTOs.add(toUserDTO(user));
        });
        return userDTOs;
    }
}
