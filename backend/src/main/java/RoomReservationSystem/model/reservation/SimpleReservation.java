package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Subject;
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
}
