package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.SemesterReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.SemesterClassReservation;
import java.util.List;

/**
 * A szemeszterekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SemesterReservationService {
    SemesterClassReservation save(SemesterReservationDTO reservation);
    void delete(SemesterClassReservation reservation);
    SemesterClassReservation findById(int id);
    SemesterClassReservation setStatus(int id, String status);
    List<SemesterClassReservation> getAll();
    List<SemesterClassReservation> findByUsername(String username);
    List<SemesterClassReservation> findByStatus(String statusName);
    List<SemesterClassReservation> findByClassroom(Classroom classroom);
    List<SemesterClassReservation> findBySemester(String semester);
    List<SemesterClassReservation> findBySubject(Subject subject);
}
