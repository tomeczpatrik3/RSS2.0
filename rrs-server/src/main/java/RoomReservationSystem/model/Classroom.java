package RoomReservationSystem.model;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.dto.ClassroomDTO;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Osztályterem entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLASSROOMS")
public class Classroom extends BaseEntity {

    /*Az osztályterem neve*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;

    /*Van-e PC*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAS_PC")
    private boolean hasPc;

    /*Van-e projektor*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAS_PROJECTOR")
    private boolean hasProjector;

    /*A teremben található székek száma*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHAIRS")
    private int chairs;

    /*Az osztályteremhez tartozó foglalások listája*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroom", targetEntity = Reservation.class)
    private List<Reservation> reservationList;

    /*Az épület, amelyben a terem található*/
    @JsonIgnore
    @JoinColumn(name = "BUILDING", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Building building;

    /**
     * A ClassroomDTO objektum Classroom objektummá konvertálását végrehajtó
     * megtódus
     *
     * @param classroomDTO A ClassroomDTO objektum
     * @param building Az épület amiben a terem található
     * @return A Classroom objektum
     */
    public static Classroom toClassroom(ClassroomDTO classroomDTO, Building building) {
        return new Classroom(
                classroomDTO.getName(),
                classroomDTO.isHasPC(),
                classroomDTO.isHasProjector(),
                classroomDTO.getChairs(),
                Collections.emptyList(),
                building
        );
    }

}
