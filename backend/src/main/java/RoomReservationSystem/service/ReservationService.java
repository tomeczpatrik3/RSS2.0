package RoomReservationSystem.service;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.model.Reservation;

import java.util.List;

/**
 * A foglalásokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface ReservationService {
    void delete(Reservation res);
    Reservation save(ReservationDTO reservatioNDTO);
    Reservation findById(int id);
    Reservation setStatus(int id, String status);
    List<Reservation> getAll();
    List<Reservation> findByUsername(String username);
    List<Reservation> findByStatus(String statusName);
}
