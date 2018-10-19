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
public class ReservationInfoDTO {
    private long id;
    private Type type;
    private String teacherName;
}
