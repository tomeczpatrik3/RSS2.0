package RoomReservationSystem.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class EventReservationDTO extends ReservationDTO{
    private String name;                /*A foglalás neve*/
}
