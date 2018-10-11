package RoomReservationSystem.dto;

import RoomReservationSystem.model.Semester;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A félévekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO extends BaseDTO {

    /*A félév neve*/
    private String name;
    /*A félév kezdetének dátuma*/
    private String startDate;
    /*A félév végének dátuma*/
    private String endDate;

    /**
     * Az osztály konstrukra
     *
     * @param id Az azonosító
     * @param name A szemeszter neve
     * @param startDate A szemeszter kezdete
     * @param endDate A szemeszter vége
     */
    public SemesterDTO(
            long id,
            String name,
            String startDate,
            String endDate
    ) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * A Semester objektumból SemesterDTO objektum létrehozásáért felelős
     * metódus
     *
     * @param semester A Semester objektum
     * @return A SemesterDTO objektum
     */
    public static SemesterDTO toSemesterDTO(Semester semester) {
        return new SemesterDTO(
                semester.getId(),
                semester.getName(),
                semester.getStartDate().toString(),
                semester.getEndDate().toString()
        );
    }

    /**
     * Több Semester objektum SemesterDTO objektummá alakításáért felelős
     * metódus
     *
     * @param semesters A Semester objektumok egy listában
     * @return A SemesterDTO obejtkumok egy listában
     */
    public static List<SemesterDTO> toSemesterDTOList(List<Semester> semesters) {
        List<SemesterDTO> semesterDTOs = new ArrayList<>();
        semesters.forEach((semester) -> {
            semesterDTOs.add(toSemesterDTO(semester));
        });
        return semesterDTOs;
    }
}
