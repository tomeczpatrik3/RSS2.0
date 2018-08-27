package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.BaseEntity;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó ősosztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RESERVATION_TYPE")
public abstract class Reservation extends BaseEntity {    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOTE")
    private String note;  /*A foglaláshoz tartozó megjegyzés*/
     
    @JsonIgnore
    @JoinColumn(name = "CLASSROOM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Classroom classroom;    /*A foglalás helye (tanterem)*/
    
    @JsonIgnore
    @JoinColumn(name = "USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User user;  /*A felhasználó aki foglalt*/
    
    @JsonIgnore
    @JoinColumn(name = "STATUSES", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Status status;  /*A foglalás státusza*/
}
