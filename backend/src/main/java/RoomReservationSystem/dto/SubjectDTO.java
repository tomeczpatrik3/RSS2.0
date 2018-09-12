package RoomReservationSystem.dto;

import RoomReservationSystem.model.Subject;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A tantárgyakhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    /*A tantárgy neve*/
    private String name;
    /*A tantárgy kódja*/
    private String code;

    /**
     * A Subject objektumból SubjectDTO objektum létrehozásáért felelős metódus
     *
     * @param subject A Subject objektum
     * @return A SubjectDTO objektum
     */
    public static SubjectDTO toSubjectDTO(Subject subject) {
        return new SubjectDTO(subject.getName(), subject.getCode());
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
