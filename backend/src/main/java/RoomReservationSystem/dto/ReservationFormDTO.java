package RoomReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz regisztrálásához tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFormDTO {
    private String username;
    private String semesterName;
    private String subjectCode;
    private String buildingName;
    private String roomName;
    private String note;
    private String[] dates;
}
