package RoomReservationSystem.model;

import RoomReservationSystem.dto.ReservationDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalás entitás
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private Date startDate; /*A foglalás kezdete (dátum)*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    private Date endDate;   /*A foglalás vége (dátum)*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "day")
    private String day; /*A foglalás napja*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "start_time")
    private String startTime; /*A foglalás kezdete (időpont)*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "end_time")
    private String endTime; /*A foglalás vége (időpont)*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_modification")
    private Date lastModification;  /*Az utolsó módosítás dátuma*/
     
    @JsonIgnore
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;    /*A foglalás helye (tanterem)*/
    
    @JsonIgnore
    @JoinColumn(name = "subject", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subject;    /*A foglalás tantárgy*/
    
    @JsonIgnore
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;  /*A felhasználó aki foglalt*/
    
    @JsonIgnore
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;  /*A foglalás státusza*/
 
    /**
     * A ReservationDTO objektum Reservation objektummá konvertálását végrehajtó megtódus
     * @param reservationDTO    A ReservationDTO objektum
     * @param classroom         A foglaláshoz tartozó tanterem
     * @param subject           A foglaláshoz tartozó tantárgy
     * @param user              A foglaláshoz tartozó felhasználó
     * @param status            A foglaláshoz tartozó státusz
     * @return                  A Reservation objektum
     */
    public static Reservation toReservation(
            ReservationDTO reservationDTO,
            Classroom classroom,
            Subject subject,
            User user,
            Status status
        ) {
        return new Reservation(
                stringToDate(reservationDTO.getStartDate()),
                stringToDate(reservationDTO.getEndDate()),
                reservationDTO.getDay(),
                reservationDTO.getStartTime(),
                reservationDTO.getEndTime(),
                new Date(),
                classroom,
                subject,
                user,
                status
        );
    }
    
    /**
     * Megfelelő formátumú szöveg dátummá konvertálása
     * @param   dateString  A dátum szöveges reprezentációja (yyyy-MM-dd)
     * @return              A Date objektum
     */
    private static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString); 
        } catch (ParseException ex) {
            return new Date();
        }
    }
}
