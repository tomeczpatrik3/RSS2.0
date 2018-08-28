package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.SimpleReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.SimpleClassReservation;
import java.util.Date;
import java.util.List;

/**
 * Az egyszerű foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author SimpleReservationomecz Patrik
 */
public interface SimpleReservationService {
    SimpleClassReservation save(SimpleReservationDTO reservation);
    void delete(SimpleClassReservation reservation);
    SimpleClassReservation findById(int id);
    SimpleClassReservation setStatus(int id, String status);
    List<SimpleClassReservation> getAll();
    List<SimpleClassReservation> findByUsername(String username);
    List<SimpleClassReservation> findByStatus(String statusName);
    List<SimpleClassReservation> findByClassroom(Classroom classroom);
    List<SimpleClassReservation> findBySubject(Subject subject);
    //List<SimpleReservation> findByDate(Date date);
    //List<SimpleReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
