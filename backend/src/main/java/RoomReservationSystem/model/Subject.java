package RoomReservationSystem.model;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.reservation.ClassReservation;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Tantárgy entitás
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUBJECTS")
public class Subject extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name; /*A tantárgy neve*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CODE", unique=true)
    private String code;    /*A tantárgy kódja (tárgykód)*/
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject", targetEntity = ClassReservation.class)
    private List<ClassReservation> reservationList; /*Az adott tantárgyra vonatkozó foglalások egy listában*/
    
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
