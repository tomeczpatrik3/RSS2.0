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
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "waiting_list")
public class Ticket extends BaseEntity {
  
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Status status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_modification")
    private Date lastModification;    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "message")
    @Size(min = 1, max = 255)
    private String message;    
    
    @JsonIgnore
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    
    @JsonIgnore
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reservation reservation;
    
}
