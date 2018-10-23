package RoomReservationSystem.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz és azok kalendárban történő megjelenítéséhez tartozó DTO
 * osztály
 *
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEventDTO {

    /*A foglalás kezdete*/
    private String start;
    /*A foglalás vége*/
    private String end;
    /*A foglalás címe*/
    private String title;
    /*A foglalás egyéb adatai*/
    private ReservationInfoDTO info;

}
