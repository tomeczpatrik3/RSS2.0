package RoomReservationSystem.service;

import RoomReservationSystem.dto.SimpleReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import java.util.Date;
import java.util.List;

/**
 * A foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface ReservationService {
    void delete(Reservation res);
    Reservation save(SimpleReservationDTO reservationFormDTO);
    Reservation findById(int id);
    Reservation setStatus(int id, String status);
    List<Reservation> getAll();
    List<Reservation> findByUsername(String username);
    List<Reservation> findByStatus(String statusName);
    List<Reservation> findByClassroom(Classroom classroom);
    List<Reservation> findByDate(Date date);
    List<Reservation> findByClassroomAndDate(String building, String classroom, Date date);
}
