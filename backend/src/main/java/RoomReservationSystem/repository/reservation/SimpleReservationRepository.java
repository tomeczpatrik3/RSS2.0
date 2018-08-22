package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SimpleReservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az egyszerű foglalásokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface SimpleReservationRepository extends JpaRepository<SimpleReservation, Integer> {
    SimpleReservation findById(int id);
    List<SimpleReservation> findByUser(User user);
    List<SimpleReservation> findByStatus(Status status);
    List<SimpleReservation> findByClassroom(Classroom classroom);
    List<SimpleReservation> findBySubject(Subject subject);
}