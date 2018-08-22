/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.model.reservation;

import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Subject;
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
}
