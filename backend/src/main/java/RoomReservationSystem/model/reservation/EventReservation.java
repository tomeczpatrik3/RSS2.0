package RoomReservationSystem.model.reservation;

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
}
