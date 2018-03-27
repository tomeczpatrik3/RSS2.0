package RoomReservationSystem.dto;

import RoomReservationSystem.model.Semester;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A félévekhez tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private String name;        /*A félév neve*/
    private String startDate;   /*A félév kezdetének dátuma*/
    private String endDate;     /*A félév végének dátuma*/
    
    /**
     * A Semester objektumból SemesterDTO objektum létrehozásáért felelős metódus
     * @param   semester    A Semester objektum
     * @return              A SemesterDTO objektum
     */
    public static SemesterDTO toSemesterDTO(Semester semester) {
        return new SemesterDTO(
                semester.getName(), 
                semester.getStartDate().toString(), 
                semester.getEndDate().toString()
        );
    }
    
    /**
     * Több Semester objektum SemesterDTO objektummá alakításáért felelős metódus
     * @param   semesters   A Semester objektumok egy listában
     * @return              A SemesterDTO obejtkumok egy listában
     */
    public static List<SemesterDTO> toSemesterDTOList(List<Semester> semesters) {
        List<SemesterDTO> semesterDTOs = new ArrayList<>();
        semesters.forEach( (semester) -> {
            semesterDTOs.add(toSemesterDTO(semester));
        });
        return semesterDTOs;
    }
}
