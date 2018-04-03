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
    private String date;                /*A foglalás dátuma*/
    private String startTime;           /*A foglalás kezdetének ideje*/
    private String endTime;             /*A foglalás végének ideje*/
    
    public String getReservationName() {
        return String.format("Simple: %s - %s - %s", super.getUsername(), this.semesterName, this.subjectCode);
    }
}
