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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A szemeszterre vonatkozó foglalásokhoz tartozó osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "semesterReservations")
public class SemesterReservation extends Reservation {
    
    @JsonIgnore
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = true)
    private Subject subject;    /*A foglalás tantárgy*/
    
    @JsonIgnore
    @JoinColumn(name = "semester", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = true)
    private Semester semester;  /*A foglaláshoz tartozó félév*/
    
    public SemesterReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Semester semester,
            Status status,
            String note
    ) {
        super(note, classroom, user, status);
        this.subject = subject;
        this.semester = semester;
    }
    
    public static SemesterReservation toSemesterReservation(
            User user,
            Subject subject,
            Classroom classroom,
            Semester semester,
            Status status,
            String note   
    ) {
        return new SemesterReservation(user, subject, classroom, semester, status, note);
    }
}
