package RoomReservationSystem.model;

import RoomReservationSystem.dto.SubjectDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code")
    private String code;    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Reservation> reservationList;
    
    public static Subject toSubject(SubjectDTO subjectDTO) {
        return new Subject(
                subjectDTO.getName(),
                subjectDTO.getCode(),
                Collections.emptyList()
        );
    }

}
