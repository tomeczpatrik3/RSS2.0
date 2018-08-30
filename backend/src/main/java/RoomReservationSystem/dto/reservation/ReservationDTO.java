package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.dto.StatusDTO;
import RoomReservationSystem.dto.UserDTO;

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
    private UserDTO user;               /*A felhasználó akihez a foglalás tarotzik*/
    private BuildingDTO building;       /*Az épület, amiben a tanterem található*/
    private ClassroomDTO classroom;     /*A tanterem amire a foglalás vonatkozik*/
    private StatusDTO status;           /*A foglalás státusza*/
    private String note;                /*A foglaláshoz tartozó jegyzet*/           
}
