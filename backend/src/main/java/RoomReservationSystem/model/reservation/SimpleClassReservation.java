package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az egyszerű foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("SIMPLE")
//@Table(name = "SIMPLE_RESERVATIONS")
public class SimpleClassReservation extends ClassReservation {
    
    public SimpleClassReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            String note
    ) {
        super(user, subject, classroom, status, note);
    }
    
    public SimpleClassReservation() {
        super();
    }
    
    public static SimpleClassReservation toSimpleReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            String note
    ) {
        return new SimpleClassReservation(user, subject, classroom, status, note);
    }
}
