package RoomReservationSystem.service;

import RoomReservationSystem.dto.TicketDTO;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.model.User;
import java.util.List;

public interface TicketService {
    Ticket save(Reservation reservation, User user);
    void delete(Ticket ticket);
    Ticket findById(int id);
    Ticket findByReservation(Reservation res);
    List<TicketDTO> getAll();
    List<TicketDTO> findByStatus(Status status);
    List<Ticket> findByStatusHelper(Status status);
    List<TicketDTO> findByUsername(String username);
    Ticket setStatus(int id, String status);
}
