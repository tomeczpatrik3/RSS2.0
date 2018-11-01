package RoomReservationSystem.service.reservation;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.util.List;

/**
 * A foglalásokhoz tartozó dátumokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface ReservationDateService {

    ReservationDate save(ReservationDate date);

    void deleteByReservation(Reservation reservation);
}
