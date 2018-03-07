package RoomReservationSystem.dto;

import RoomReservationSystem.model.Subject;
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
}
