package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.SemesterReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.SemesterReservation;
import java.util.List;

/**
 * A szemeszterekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SemesterReservationService {
    SemesterReservation save(SemesterReservationDTO reservation);
    void delete(SemesterReservation reservation);
    SemesterReservation findById(int id);
    SemesterReservation setStatus(int id, String status);
    List<SemesterReservation> getAll();
    List<SemesterReservation> findByUsername(String username);
    List<SemesterReservation> findByStatus(String statusName);
    List<SemesterReservation> findByClassroom(Classroom classroom);
    List<SemesterReservation> findBySemester(String semester);
    List<SemesterReservation> findBySubject(Subject subject);
}
