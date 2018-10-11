package RoomReservationSystem.dto;

import RoomReservationSystem.model.User;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A felhasználókhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    /*A felhasználónév*/
    private String username;
    /*A felhasználó neve*/
    private String name;
    /*A felhasználó e-mail címe*/
    private String email;
    /*A felhasználó jelszava*/
    private String password;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param username A felhasználónév
     * @param name A felhasználó neve
     * @param email Az e-mail cím
     * @param password A jelszó
     */
    public UserDTO(
            long id,
            String username,
            String name,
            String email,
            String password
    ) {
        super(id);
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * A User objektumból UserDTO objektum létrehozásáért felelős metódus
     *
     * @param user A User obejktum
     * @return A UserDTO objektum
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                "hehexd"
        );
    }

    /**
     * Több User objektum UserDTO objektummá alakításáért felelős metódus
     *
     * @param users A User objektumok egy listában
     * @return A UserDTO objektumok egy listában
     */
    public static List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach((user) -> {
            userDTOs.add(toUserDTO(user));
        });
        return userDTOs;
    }
}
