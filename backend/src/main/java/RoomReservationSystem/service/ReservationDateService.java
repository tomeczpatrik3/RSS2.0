package RoomReservationSystem.service;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import RoomReservationSystem.model.Semester;
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
    List<ReservationDate> save(Reservation reservation, Semester semester, String day, String startTime, String endTime);
    List<Reservation> findByDate(Date date);
}
