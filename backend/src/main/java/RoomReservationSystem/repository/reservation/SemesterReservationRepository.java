package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SemesterReservation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A szemeszterekre vonatkozó foglalásokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface SemesterReservationRepository extends JpaRepository<SemesterReservation, Integer> {
    SemesterReservation findById(int id);
    List<SemesterReservation> findByUser(User user);
    List<SemesterReservation> findByStatus(Status status);
    List<SemesterReservation> findByClassroom(Classroom classroom);
    List<SemesterReservation> findBySubject(Subject subject);
    List<SemesterReservation> findBySemester(Semester semester);
}