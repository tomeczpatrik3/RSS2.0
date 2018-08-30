package RoomReservationSystem.model;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.util.DateUtils;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Szemeszter entitás
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEMESTERS")
public class Semester extends BaseEntity{

    @Basic(optional = false)
    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "NAME", unique=true)
    private String name; /*Az szemeszter neve (pl: 2017-2018-2)*/    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate; /*A szemeszter kezdete (dátum)*/
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate; /*A szemeszter vége (dátum)*/
    
    /**
     * A SemesterDTO objektum Semester objektummá konvertálását végrehajtó megtódus
     * @param   semesterDTO A SemesterDTO objektum
     * @return              A Semester objektum
     */
    public static Semester toSemester(SemesterDTO semesterDTO) {
        return new Semester(
                semesterDTO.getName(),
                DateUtils.getDate(semesterDTO.getStartDate()),
                DateUtils.getDate(semesterDTO.getEndDate())
        );
    }
}
