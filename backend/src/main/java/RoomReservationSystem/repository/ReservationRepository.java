package RoomReservationSystem.repository;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);
    List<Reservation> findByUser(User user);
    List<Reservation> findByStatus(Status status);
}