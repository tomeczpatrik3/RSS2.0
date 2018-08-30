package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

/**
 * A foglalásokhoz tartozó dátumokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESERVATION_DATES")
public class ReservationDate extends BaseEntity{
    @JsonIgnore
    @JoinColumn(name = "RESERVATION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Reservation reservation;  /*A foglalás státusza*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "START")
    @Temporal(javax.persistence.TemporalType.DATE)
    private DateTime start; /*A foglalás kezdete (dátum)*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "END")
    @Temporal(javax.persistence.TemporalType.DATE)
    private DateTime end; /*A foglalás vége (dátum)*/
}
