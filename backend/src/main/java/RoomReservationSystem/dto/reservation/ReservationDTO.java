package RoomReservationSystem.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó DTO ősosztály
 *
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ReservationDTO {

    /*A felhasználó akihez a foglalás tarotzik*/
    private String username;
    /*Az épület, amiben a tanterem található*/
    private String building;
    /*A tanterem amire a foglalás vonatkozik*/
    private String classroom;
    /*A foglalás státusza*/
    private String status;
    /*A foglaláshoz tartozó jegyzet*/
    private String note;
}
