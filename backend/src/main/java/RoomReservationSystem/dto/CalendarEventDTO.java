package RoomReservationSystem.dto;

import RoomReservationSystem.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventDTO {

    private String start;
    private String end;
    private String title;
    private ReservationInfoDTO info;

}
