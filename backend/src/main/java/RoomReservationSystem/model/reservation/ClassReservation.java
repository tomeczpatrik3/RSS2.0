package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A tanórákra vonatkozó foglalásokhoz tartozó ősosztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RESERVATION_TYPE")
public abstract class ClassReservation extends Reservation {
    @JsonIgnore
    @JoinColumn(name = "SUBJECT", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Subject subject;    /*A foglalás tantárgy*/
    
    protected ClassReservation(            
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            String note) {
        super(user, classroom, status, note);
        this.subject = subject;
    }
    
    protected ClassReservation() {
        super();
    }
}
