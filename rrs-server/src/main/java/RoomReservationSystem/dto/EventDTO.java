package RoomReservationSystem.dto;

import RoomReservationSystem.dto.reservation.ReservationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az eseményekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    /*A foglalás kezdete*/
    private String start;
    /*A foglalás vége*/
    private String end;
    /*A foglalás címe*/
    private String title;
    /*A foglalás egyéb adatai*/
    private ReservationInfoDTO info;

}
