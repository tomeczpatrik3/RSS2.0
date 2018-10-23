package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A tanórákra vonatkozó foglalásokhoz tartozó ősosztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CLASS")
public class ClassReservation extends Reservation {

    /*A tantárgy*/
    @JsonIgnore
    @JoinColumn(name = "SUBJECT", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Subject subject;

    /*A szemeszter*/
    @JsonIgnore
    @JoinColumn(name = "SEMESTER", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Semester semester;

    /**
     * Az osztály konstruktora
     *
     * @param user A felhasználó
     * @param subject A tantárgy
     * @param classroom A tanterem
     * @param status A státusz
     * @param semester A szemeszter
     * @param dateList A foglalás lista
     * @param note A megjegyzés
     */
    protected ClassReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            Semester semester,
            List<ReservationDate> dateList,
            String note) {
        super(user, classroom, status, dateList, note);
        this.subject = subject;
        this.semester = semester;
    }

    /**
     * Az osztály üres konstruktora
     */
    protected ClassReservation() {
        super();
    }

    /**
     * A ClassReservation objektum létrehozásáért felelős metódus
     *
     * @param user A felhasználó
     * @param subject A tantárgy
     * @param classroom A tanterem
     * @param status A státusz
     * @param semester A szemeszter
     * @param dateList A foglalás lista
     * @param note A megjegyzés
     * @return A megfelelő ClassReservation objektum
     */
    public static ClassReservation toClassReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Status status,
            Semester semester,
            List<ReservationDate> dateList,
            String note
    ) {
        return new ClassReservation(user, subject, classroom, status, semester, dateList, note);
    }
}
