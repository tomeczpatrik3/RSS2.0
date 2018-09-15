package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.BaseEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARCHIVED_RESERVATIONS")
public class ArchivedReservation extends BaseEntity {

    /*A foglaláshoz tartozó megjegyzés*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOTE")
    private String note;

    /*A felhasználó aki foglalt*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;

    /*A foglalás helye (tanterem)*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLASSROOM")
    private String classroom;

    //---
    /*A tantárgy*/
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "SUBJECT")
    private String subject;

    /*A szemeszter*/
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "SEMESTER")
    private String semester;

    /*A foglalás neve*/
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
}
