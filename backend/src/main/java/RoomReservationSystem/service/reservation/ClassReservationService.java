package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.ClassReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.ClassReservation;
import java.util.List;

/**
 * A tanórákra vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author SimpleReservationomecz Patrik
 */
public interface ClassReservationService {
    ClassReservation save(ClassReservationDTO reservation);
    void delete(ClassReservation reservation);
    ClassReservation findById(int id);
    ClassReservation setStatus(int id, String status);
    List<ClassReservation> getAll();
    List<ClassReservation> findByUsername(String username);
    List<ClassReservation> findByStatus(String statusName);
    List<ClassReservation> findByClassroom(Classroom classroom);
    List<ClassReservation> findBySubject(Subject subject);
    //List<SimpleReservation> findByDate(Date date);
    //List<SimpleReservation> findByClassroomAndDate(String building, String classroom, Date date);
}
