package RoomReservationSystem.service;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.model.Reservation;
import java.util.List;

public interface ReservationService {
    void delete(Reservation res);
    void deleteAll();
    Reservation save(ReservationDTO res);
    Reservation findById(int id);
    List<ReservationDTO> getAll();
    List<ReservationDTO> findByUsername(String username);
}
