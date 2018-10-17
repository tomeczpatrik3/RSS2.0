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
public class CalendarEventDTO extends BaseDTO {

    private String start;
    private String end;
    private String title;
    private Type type;
    
    public CalendarEventDTO(long id, String start, String end, String title, Type type) {
        super(id);
        this.start = start;
        this.end = end;
        this.title = title;
        this.type = type;
    }
}
