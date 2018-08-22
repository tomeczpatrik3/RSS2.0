package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.BaseEntity;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.Type;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.ReservationDate;
import static RoomReservationSystem.util.DateUtils.getDateTimeString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó ősosztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Reservation extends BaseEntity {    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "note")
    private String note;  /*A foglaláshoz tartozó megjegyzés*/
     
    @JsonIgnore
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;    /*A foglalás helye (tanterem)*/
    
    @JsonIgnore
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;  /*A felhasználó aki foglalt*/
    
    @JsonIgnore
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;  /*A foglalás státusza*/
    
//    @JsonIgnore
//    @JoinColumn(name = "type", referencedColumnName = "id")
//    @ManyToOne(optional = false)    
//    private Type type;  /*A foglalás típusa*/

//    
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
//    private List<ReservationDate> dateList; /*A foglalt dátumok*/
 
//    /**
//     * A ReservationDTO objektum Reservation objektummá konvertálását végrehajtó megtódus
//     * @param name              A foglalás neve
//     * @param type              A foglalás típusa
//     * @param note              A foglaláshoz tartozó megjegyzés
//     * @param classroom         A foglaláshoz tartozó tanterem
//     * @param subject           A foglaláshoz tartozó tantárgy
//     * @param user              A foglaláshoz tartozó felhasználó
//     * @param status            A foglaláshoz tartozó státusz
//     * @param semester          A foglaláshoz tartozó félév
//     * @return                  A Reservation objektum
//     */
//    public static Reservation toReservation(
//            User user,
//            Semester semester,
//            Subject subject,
//            Classroom classroom,
//            Status status,
//            Type type,
//            String name,
//            String note
//        ) {
//        return new Reservation(
//                name,
//                note,
//                classroom,
//                subject,
//                user,
//                status,
//                type,
//                semester,
//                Collections.emptyList()
//        );
//    }
//    
//    /**
//     * Egy adott foglaláshoz tartozó dátumok String tömbbé konvertálása:
//     * @return  A dátumok egy String tömbben
//     */
//    public String[] getDates() {
//        String[] dates = new String[dateList.size()*2];
//        int i = 0;
//        for (ReservationDate resDate: dateList) {
//            dates[i] = getDateTimeString(resDate.getStartDateTime());
//            dates[i+1] = getDateTimeString(resDate.getEndDateTime());
//            i+=2;
//        }
//        return dates;
//    }
}
