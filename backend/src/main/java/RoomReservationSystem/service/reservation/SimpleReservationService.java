package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.SimpleReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.SimpleReservation;
import java.util.Date;
import java.util.List;

/**
 * Az egyszerű foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author SimpleReservationomecz Patrik
 */
public interface SimpleReservationService {
    SimpleReservation save(SimpleReservationDTO reservation);
    void delete(SimpleReservation reservation);
    SimpleReservation findById(int id);
    SimpleReservation setStatus(int id, String status);
    List<SimpleReservation> getAll();
    List<SimpleReservation> findByUsername(String username);
    List<SimpleReservation> findByStatus(String statusName);
    List<SimpleReservation> findByClassroom(Classroom classroom);
    List<SimpleReservation> findBySubject(Subject subject);
    //List<SimpleReservation> findByDate(Date date);
    //List<SimpleReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
