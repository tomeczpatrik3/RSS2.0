package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.reservation.EventReservation;
import java.util.Date;
import java.util.List;

/**
 * Az eseményre vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface EventReservationService {
    EventReservation save(EventReservationDTO reservation);
    void delete(EventReservation reservation);
    EventReservation findById(int id);
    EventReservation setStatus(int id, String status);
    EventReservation findByName(String name);
    List<EventReservation> getAll();
    List<EventReservation> findByUsername(String username);
    List<EventReservation> findByStatus(String statusName);
    List<EventReservation> findByClassroom(Classroom classroom);
    
//    List<EventReservation> findByDate(Date date);
//    List<EventReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
