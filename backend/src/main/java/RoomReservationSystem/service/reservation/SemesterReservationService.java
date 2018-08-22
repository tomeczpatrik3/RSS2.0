package RoomReservationSystem.service.reservation;

import RoomReservationSystem.model.reservation.SemesterReservation;
import java.util.List;

/**
 * A szemeszterekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SemesterReservationService extends ReservationService {
    List<SemesterReservation> findBySemester(String semester);
}
