/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A szemeszterre vonatkozó foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("SEMESTER")
//@Table(name = "SEMESTER_RESERVATIONS")
public class SemesterClassReservation extends ClassReservation {
    
    @JsonIgnore
    @JoinColumn(name = "SEMESTER", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Semester semester;  /*A foglaláshoz tartozó félév*/
    
    public SemesterClassReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Semester semester,
            Status status,
            String note
    ) {
        super(user, subject, classroom, status, note);
        this.semester = semester;
    }
    
    public SemesterClassReservation() {
        super();
    }
    
    public static SemesterClassReservation toSemesterReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Semester semester,
            Status status,
            String note   
    ) {
        return new SemesterClassReservation(user, subject, classroom, semester, status, note);
    }
}
