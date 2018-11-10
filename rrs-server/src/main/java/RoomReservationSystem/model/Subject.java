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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Tantárgy entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "SUBJECTS")
public class Subject extends BaseEntity {

    /*A tantárgy neve*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME")
    private String name;

    /*A tantárgy kódja (tárgykód)*/
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODE", unique = true)
    private String code;

    /*Az adott tantárgyra vonatkozó foglalások egy listában*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject", targetEntity = ClassReservation.class)
    private List<ClassReservation> reservationList;

    /**
     * Az osztály üres konstruktora
     */
    public Subject() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param name A tantárgy neve
     * @param code A tárgykód
     * @param reservationList A tantárgyhoz tartozó foglalások
     * @param id Az azonosító
     */
    public Subject(String name, String code, List<ClassReservation> reservationList, Integer id) {
        super(id);
        this.name = name;
        this.code = code;
        this.reservationList = reservationList;
    }

    /**
     * A SubjectDTO objektum Subject objektummá konvertálását végrehajtó
     * megtódus
     *
     * @param subjectDTO A SubjectDTO objektum
     * @return A Subject objektum
     */
    public static Subject toSubject(SubjectDTO subjectDTO) {
        return new Subject(
                subjectDTO.getName(),
                subjectDTO.getCode(),
                Collections.emptyList()
        );
    }

}
