package RoomReservationSystem.service.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.reservation.Reservation;
import java.util.List;

/**
 * A foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface ReservationService {
    <T extends Reservation> T save(T reservation);
    <T extends Reservation> void delete(T reservation);
    <T extends Reservation> T findById(int id);
    <T extends Reservation> T setStatus(int id, String status);
    <T extends Reservation> List<T> getAll();
    <T extends Reservation> List<T> findByUsername(String username);
    <T extends Reservation> List<T> findByStatus(String statusName);
    <T extends Reservation> List<T> findByClassroom(Classroom classroom);
}
