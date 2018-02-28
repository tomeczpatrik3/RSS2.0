package RoomReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classrooms")
public class Classroom extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_pc")
    private boolean hasPc;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_projector")
    private boolean hasProjector;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "chairs")
    private int chairs;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroomId")
    private List<Reservation> reservationList;
    
    @JsonIgnore
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Building buildingId;
    
}
