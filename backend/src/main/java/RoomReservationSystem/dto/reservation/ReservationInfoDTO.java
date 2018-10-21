package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInfoDTO {

    private long id;
    private Type type;
    private String name;
    private String building;
    private String classroom;
    private String note;

    private String eventName;

    private String subject;
    private String semester;

    public ReservationInfoDTO(
            long id,
            Type type,
            String name,
            String building,
            String classroom,
            String note
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.building = building;
        this.classroom = classroom;
    }

    public ReservationInfoDTO(long id, Type type, String name, String building, String classroom, String note, String subject, String semester) {
        this(id, type, name, building, classroom, note);
        this.subject = subject;
        this.semester = semester;
        this.eventName = "";
    }

    public ReservationInfoDTO(long id, Type type, String name, String building, String classroom, String note, String eventName) {
        this(id, type, name, building, classroom, note);
        this.eventName = eventName;
        this.subject = "";
        this.semester = "";
    }
    
    
}
