package RoomReservationSystem.service.reservation;

import RoomReservationSystem.model.reservation.EventReservation;
import java.util.Date;
import java.util.List;

/**
 * Az eseményre vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface EventReservationService extends ReservationService {
    EventReservation findByName(String name);
    List<EventReservation> findByDate(Date date);
    List<EventReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
