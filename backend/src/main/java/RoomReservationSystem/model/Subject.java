package RoomReservationSystem.model;

import RoomReservationSystem.model.reservation.Reservation;
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

/**
 * Tantárgy entitás
 * @author Tomecz Patrik
 */
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
    private String name; /*A tantárgy neve*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code", unique=true)
    private String code;    /*A tantárgy kódja (tárgykód)*/
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Reservation> reservationList; /*Az adott tantárgyra vonatkozó foglalások egy listában*/
    
    /**
     * A SubjectDTO objektum Subject objektummá konvertálását végrehajtó megtódus
     * @param   subjectDTO  A SubjectDTO objektum
     * @return              A Subject objektum
     */
    public static Subject toSubject(SubjectDTO subjectDTO) {
        return new Subject(
                subjectDTO.getName(),
                subjectDTO.getCode(),
                Collections.emptyList()
        );
    }

}
