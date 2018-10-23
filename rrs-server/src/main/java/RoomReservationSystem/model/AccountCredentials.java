package RoomReservationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az authentikációhoz használt osztály
 *
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials {

    /*A felhasználónév*/
    private String username;
    /*A jelszó*/
    private String password;
}
