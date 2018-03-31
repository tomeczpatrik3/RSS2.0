package RoomReservationSystem.repository;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A kölcsönzésekért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);
    List<Reservation> findByUser(User user);
    List<Reservation> findByStatus(Status status);
    List<Reservation> findByClassroom(Classroom classroom);
}