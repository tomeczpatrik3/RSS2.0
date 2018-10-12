package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.EventReservation;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az eseményekre vonatkozó foglalásokért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface EventReservationRepository extends JpaRepository<EventReservation, Integer> {

    EventReservation findById(int id);

    EventReservation findByName(String name);

    List<EventReservation> findByUser(User user);

    List<EventReservation> findByStatus(Status status);

    List<EventReservation> findByClassroom(Classroom classroom);
    
    boolean existsById(int id);

    @Transactional
    void deleteByUser(User user);

    @Transactional
    void deleteByClassroom(Classroom classroom);

    @Transactional
    void deleteByStatus(Status status);

    @Transactional
    void deleteByName(String name);
}
