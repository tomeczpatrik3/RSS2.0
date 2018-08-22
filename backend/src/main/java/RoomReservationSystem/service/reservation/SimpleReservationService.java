package RoomReservationSystem.service.reservation;

import RoomReservationSystem.model.reservation.SimpleReservation;
import java.util.Date;
import java.util.List;

/**
 * Az egyszerű foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SimpleReservationService extends ReservationService {
    List<SimpleReservation> findByDate(Date date);
    List<SimpleReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
