package RoomReservationSystem.service;

import RoomReservationSystem.model.Reservation;
import java.util.List;

public interface ReservationService {
    void delete(Reservation res);
    void deleteAll();
    Reservation save(Reservation res);
    Iterable<Reservation> findAll();
}
