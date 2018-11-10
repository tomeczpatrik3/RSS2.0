package RoomReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Státusz entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "STATUSES")
public class Status extends BaseEntity {

    /*A státusz neve*/
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", unique = true)
    private String name;

    /*A státuszhoz tartozó üzenet*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "message")
    private String message;

    /*Az adott státusszal rendelkező foglalások egy listában*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status", targetEntity = Reservation.class)
    private List<Reservation> reservationList;

    /**
     * Az osztály üres konstruktora
     */
    public Status() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param name A státusz megnevezése
     * @param message A státuszhoz tartozó üzenet
     * @param reservationList A foglalások, amik ezzel a státusszal rendelkeznek
     * @param id Az azonosító
     */
    public Status(String name, String message, List<Reservation> reservationList, Integer id) {
        super(id);
        this.name = name;
        this.message = message;
        this.reservationList = reservationList;
    }
}
