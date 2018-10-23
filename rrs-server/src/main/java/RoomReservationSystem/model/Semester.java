package RoomReservationSystem.model;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Szemeszter entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "SEMESTERS")
public class Semester extends BaseEntity {

    /*Az szemeszter neve (pl: 2017-2018/2)*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "NAME", unique = true)
    private String name;

    /*A szemeszter kezdete (dátum)*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;

    /*A szemeszter vége (dátum)*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    /*Az adott szemeszterhez tartozó foglalások egy listában*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester", targetEntity = ClassReservation.class)
    private List<ClassReservation> reservationList;

    /**
     * Az osztály üres konstruktora
     */
    public Semester() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param name A szemeszter "neve"
     * @param startDate A szemeszter kezdetének dátuma
     * @param endDate A szemeszter végének dátuma
     * @param reservationList A foglalások, amelyek erre a szemeszterre
     * vonatkoznak
     * @param id Az azonosító
     */
    public Semester(String name, Date startDate, Date endDate, List<ClassReservation> reservationList, Integer id) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationList = reservationList;
    }

    /**
     * A SemesterDTO objektum Semester objektummá konvertálását végrehajtó
     * megtódus
     *
     * @param semesterDTO A SemesterDTO objektum
     * @return A Semester objektum
     */
    public static Semester toSemester(SemesterDTO semesterDTO) {
        return new Semester(
                semesterDTO.getName(),
                DateUtils.getDate(semesterDTO.getStartDate()),
                DateUtils.getDate(semesterDTO.getEndDate()),
                Collections.emptyList()
        );
    }
}
