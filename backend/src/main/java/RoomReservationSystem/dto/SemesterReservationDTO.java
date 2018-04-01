package RoomReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Egy egész szemeszterre és tantárgyra vonatkozó foglaláshoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemesterReservationDTO extends BaseReservationDTO {
    private String semesterName;    /*A szemeszter, amin belül a foglalás történik*/
    private String subjectCode;     /*A tantárgy kódja*/
    private String day;             /*A nap*/
    private String startTime;       /*A kezdés ideje*/
    private String endTime;         /*A befejezés ideje*/
}
