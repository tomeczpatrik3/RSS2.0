package RoomReservationSystem.dto;

import RoomReservationSystem.model.Subject;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A tantárgyakhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO extends BaseDTO {

    /*A tantárgy neve*/
    private String name;
    /*A tantárgy kódja*/
    private String code;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param name A tantárgy neve
     * @param code A tantárgy kódja
     */
    public SubjectDTO(
            long id,
            String name,
            String code
    ) {
        super(id);
        this.name = name;
        this.code = code;
    }

    /**
     * A Subject objektumból SubjectDTO objektum létrehozásáért felelős metódus
     *
     * @param subject A Subject objektum
     * @return A SubjectDTO objektum
     */
    public static SubjectDTO toSubjectDTO(Subject subject) {
        return new SubjectDTO(subject.getId(), subject.getName(), subject.getCode());
    }

    /**
     * Több Subject objektum SubjectDTO objektummá alakításáért felelős metódus
     *
     * @param subjects A Subject objektumok egy listában
     * @return A SubjectDTO objektumok egy listában
     */
    public static List<SubjectDTO> toSubjectDTOList(List<Subject> subjects) {
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        subjects.forEach((subject) -> {
            subjectDTOs.add(toSubjectDTO(subject));
        });
        return subjectDTOs;
    }
}
