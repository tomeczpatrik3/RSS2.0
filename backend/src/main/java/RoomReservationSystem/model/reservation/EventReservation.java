package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventReservations")
public class EventReservation extends Reservation {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;  /*A foglalás neve*/
    
    public EventReservation(
            User user,
            Classroom classroom,
            Status status,
            String name,
            String note
    ) {
        super(note, classroom, user, status);
        this.name = name;
    }
    
    public static EventReservation toEventReservation(
            User user,
            Classroom classroom,
            Status status,
            String name,
            String note
    ) {
        return new EventReservation(user, classroom, status, name, note);
    }
}
