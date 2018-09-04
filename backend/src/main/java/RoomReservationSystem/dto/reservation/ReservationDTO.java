package RoomReservationSystem.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó DTO ősosztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ReservationDTO {
    private String username;               /*A felhasználó akihez a foglalás tarotzik*/
    private String building;       /*Az épület, amiben a tanterem található*/
    private String classroom;     /*A tanterem amire a foglalás vonatkozik*/
    private String status;           /*A foglalás státusza*/
    private String note;                /*A foglaláshoz tartozó jegyzet*/           
}
