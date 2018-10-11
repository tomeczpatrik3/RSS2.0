package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó DTO ősosztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ReservationDTO extends BaseDTO {

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
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param username A felhasználónév
     * @param building Az épület
     * @param classroom A tanterem
     * @param status A státusz
     * @param note A megjegyzés
     */
    public ReservationDTO(
            long id,
            String username,
            String building,
            String classroom,
            String status,
            String note
    ) {
        super(id);
        this.username = username;
        this.building = building;
        this.classroom = classroom;
        this.status = status;
        this.note = note;
    }
}
