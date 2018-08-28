package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SemesterClassReservation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A szemeszterekre vonatkozó foglalásokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface SemesterReservationRepository extends JpaRepository<SemesterClassReservation, Integer> {
    SemesterClassReservation findById(int id);
    List<SemesterClassReservation> findByUser(User user);
    List<SemesterClassReservation> findByStatus(Status status);
    List<SemesterClassReservation> findByClassroom(Classroom classroom);
    List<SemesterClassReservation> findBySubject(Subject subject);
    List<SemesterClassReservation> findBySemester(Semester semester);
}