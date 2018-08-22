package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A szemeszterre vonatkozó foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SemesterReservationDTO extends ReservationDTO{
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    private SemesterDTO semester;            /*A félév*/
}
