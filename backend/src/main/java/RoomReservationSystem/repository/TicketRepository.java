package RoomReservationSystem.repository;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByStatus(Status status);
    Ticket findById(int id);
    List<Ticket> findByUser(User user);
    Ticket findByReservation(Reservation res);
}
