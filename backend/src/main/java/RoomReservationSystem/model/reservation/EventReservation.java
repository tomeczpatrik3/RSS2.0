package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("EVENT")
//@Table(name = "EVENT_RESERVATIONS")
public class EventReservation extends Reservation {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;  /*A foglalás neve*/

    public EventReservation(
            User user,
            Classroom classroom,
            Status status,
            List<ReservationDate> dateList,
            String name,
            String note
    ) {
        super(user, classroom, status, dateList, note);
        this.name = name;
    }
    
    public EventReservation() {
        super();
    }
    
    public static EventReservation toEventReservation(
            User user,
            Classroom classroom,
            Status status,
            List<ReservationDate> dateList,
            String name,
            String note
    ) {
        return new EventReservation(user, classroom, status, dateList, name, note);
    }
}
