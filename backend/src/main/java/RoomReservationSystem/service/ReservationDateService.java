package RoomReservationSystem.service;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import static RoomReservationSystem.util.DateUtils.getDateTime;
import java.util.Date;

import java.util.List;

/**
 * A foglalásokhoz tartozó dátumokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface ReservationDateService {
    void delete(ReservationDate reservationDate);
    void deleteByReservation(Reservation reservation);
    ReservationDate save(ReservationDate reservationDate);
    ReservationDate save(Reservation reservation, String startDateTime, String endDateTime);
    List<ReservationDate> saveReservationDates(Reservation reservation, String[] dates);
    List<Reservation> findByDate(Date date);
}
