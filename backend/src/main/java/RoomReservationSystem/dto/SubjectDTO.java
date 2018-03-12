package RoomReservationSystem.dto;

import RoomReservationSystem.model.Subject;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String name;
    private String code;
    
    public static SubjectDTO toSubjectDTO(Subject subject) {
        return new SubjectDTO(subject.getName(), subject.getCode());
    }
    
    public static List<SubjectDTO> toSubjectDTOList(List<Subject> subjects) {
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        subjects.forEach((subject) -> {
            subjectDTOs.add(toSubjectDTO(subject));
        });
        return subjectDTOs;
    }
}
