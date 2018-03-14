package RoomReservationSystem.dto;

import RoomReservationSystem.model.Semester;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private String name;
    private String startDate;
    private String endDate;
    
    public static SemesterDTO toSemesterDTO(Semester semester) {
        return new SemesterDTO(
                semester.getName(), 
                semester.getStartDate().toString(), 
                semester.getEndDate().toString()
        );
    }
    
    public static List<SemesterDTO> toSemesterDTOList(List<Semester> semesters) {
        List<SemesterDTO> semesterDTOs = new ArrayList<>();
        semesters.forEach( (semester) -> {
            semesterDTOs.add(toSemesterDTO(semester));
        });
        return semesterDTOs;
    }
}
