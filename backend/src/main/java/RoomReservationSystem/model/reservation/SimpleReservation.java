package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az egyszerű foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "simpleReservations")
public class SimpleReservation extends Reservation {
    
    @JsonIgnore
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = true)
    private Subject subject;    /*A foglalás tantárgy*/
    
    public SimpleReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            String note
    ) {
        super(note, classroom, user, status);
        this.subject = subject;
    }
    
    public static SimpleReservation toSimpleReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            String note
    ) {
        return new SimpleReservation(user, subject, classroom, status, note);
    }
}
