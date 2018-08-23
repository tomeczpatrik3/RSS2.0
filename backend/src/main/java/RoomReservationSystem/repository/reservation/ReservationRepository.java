package RoomReservationSystem.repository.reservation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;

import java.util.List;

public interface ReservationRepository {
    Reservation findById(int id);
    List<Reservation> findByUser(User user);
    List<Reservation> findByStatus(Status status);
    List<Reservation> findByClassroom(Classroom classroom);
}