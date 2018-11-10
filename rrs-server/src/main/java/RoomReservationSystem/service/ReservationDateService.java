package RoomReservationSystem.service;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
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
