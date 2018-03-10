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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private Date startDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    private Date endDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "day")
    private String day;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "start_time")
    private String startTime;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "end_time")
    private String endTime;
    
    @JsonIgnore
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;
    
    @JsonIgnore
    @JoinColumn(name = "subject", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subject;
    
    @JsonIgnore
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
 
    
    public static Reservation toReservation(
            ReservationDTO reservationDTO,
            Classroom classroom,
            Subject subject,
            User user) {
        return new Reservation(
                stringToDate(reservationDTO.getStartDate()),
                stringToDate(reservationDTO.getEndDate()),
                reservationDTO.getDay(),
                reservationDTO.getStartTime(),
                reservationDTO.getEndTime(),
                classroom,
                subject,
                user
        );
    }
    
    private static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString); 
        } catch (ParseException ex) {
            return new Date();
        }
    }
}
