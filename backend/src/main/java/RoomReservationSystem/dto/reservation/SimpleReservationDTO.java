package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Az egyszerű foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReservationDTO extends ReservationDTO{
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
}
