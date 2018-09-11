package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.ClassReservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A tanórákra vonatkozó foglalásokért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface ClassReservationRepository extends JpaRepository<ClassReservation, Integer> {

    ClassReservation findById(int id);

    List<ClassReservation> findByUser(User user);

    List<ClassReservation> findByStatus(Status status);

    List<ClassReservation> findByClassroom(Classroom classroom);

    List<ClassReservation> findBySubject(Subject subject);
}
