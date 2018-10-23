package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A foglalásokhoz tartozó dátumokért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface ReservationDateRepository extends JpaRepository<ReservationDate, Integer>{

    @Transactional
    void deleteByReservation(Reservation reservation);
}
