package RoomReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó dátumok
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_dates")
public class ReservationDate extends BaseEntity {
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "startTime")
    private Date startTime; /*A foglalás kezdete*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "endTime")
    private Date endTime; /*A foglalás vége*/
    
    @JsonIgnore
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reservation reservation;  /*A foglalás amihez a dátum tartozik*/
}
