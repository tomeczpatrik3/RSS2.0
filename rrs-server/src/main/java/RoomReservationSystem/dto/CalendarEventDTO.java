package RoomReservationSystem.dto;

import RoomReservationSystem.dto.reservation.ReservationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventDTO {

    private String start;
    private String end;
    private String title;
    private ReservationInfoDTO info;

}
