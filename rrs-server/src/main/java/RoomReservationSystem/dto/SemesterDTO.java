package RoomReservationSystem.dto;

import RoomReservationSystem.model.Semester;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A félévekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SemesterDTO extends BaseDTO {

    /*A félév neve*/
    private String name;
    /*A félév kezdetének dátuma*/
    private String startDate;
    /*A félév végének dátuma*/
    private String endDate;
    /*A félév státusza*/
    private boolean opened;

    /**
     * Az osztály konstrukra
     *
     * @param id Az azonosító
     * @param name A szemeszter neve
     * @param startDate A szemeszter kezdete
     * @param endDate A szemeszter vége
     * @param opened A szemeszter státusza
     */
    public SemesterDTO(
            long id,
            String name,
            String startDate,
            String endDate,
            boolean opened
    ) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.opened = opened;
    }

    /**
     * Az osztály üres konstruktora
     */
    public SemesterDTO() {
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
                semester.getEndDate().toString(),
                semester.isOpened()
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
