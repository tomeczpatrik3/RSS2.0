package RoomReservationSystem.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("EVENT")
public class EventReservation extends Reservation {

    /*A foglalás neve*/
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME")
    private String name;

    /**
     * Az osztály konstruktora
     *
     * @param user A felhasználó
     * @param classroom A tanterem
     * @param status A státusz
     * @param dateList A foglalás lista
     * @param name A foglalás neve
     * @param note A megjegyzés
     */
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

    /**
     * Az osztály üres konstruktora
     */
    public EventReservation() {
        super();
    }

    /**
     * Az EventReservation objektum létrehozásáért felelős metódus
     *
     * @param user A felhasználó
     * @param classroom A tanterem
     * @param status A státusz
     * @param dateList A foglalás lista
     * @param name A foglalás neve
     * @param note A megjegyzés
     * @return A megfelelő EventReservation objektum
     */
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
