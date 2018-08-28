package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SimpleClassReservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az egyszerű foglalásokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface SimpleReservationRepository extends JpaRepository<SimpleClassReservation, Integer> {
    SimpleClassReservation findById(int id);
    List<SimpleClassReservation> findByUser(User user);
    List<SimpleClassReservation> findByStatus(Status status);
    List<SimpleClassReservation> findByClassroom(Classroom classroom);
    List<SimpleClassReservation> findBySubject(Subject subject);
}