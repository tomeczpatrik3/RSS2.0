package RoomReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Egy napra és tantárgyra vonatkozó foglaláshoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReservationDTO extends BaseReservationDTO {
    private String semesterName;        /*A szemeszter, amin belül a foglalás történik*/
    private String subjectCode;         /*A tantárgy kódja*/
    private String startDateTime;       /*Az esemény kezdetének dátuma és ideje*/
    private String endDateTime;         /*Az esemény végének dátuma és ideje*/
}
