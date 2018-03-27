package RoomReservationSystem.repository;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A foglalásokhoz tartozó időpontokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface ReservationDateRepository extends JpaRepository<ReservationDate, Integer> {
    void deleteByReservation(Reservation reservation);
}
