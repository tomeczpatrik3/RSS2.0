package RoomReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Egy napra és tetszőleges eseményre vonatkozó foglaláshoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventReservationDTO extends BaseReservationDTO {
    private String eventName;       /*Az esemény neve*/
    private String startDateTime;   /*Az esemény kezdetének dátuma és ideje*/
    private String endDateTime;     /*Az esemény végének dátuma és ideje*/
    
    public String getReservationName() {
        return String.format("%s", this.eventName);
    }
}
