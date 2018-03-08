package RoomReservationSystem.service.impl;

import static RoomReservationSystem.config.TicketMessages.TICKET_CREATION_MESSAGE;
import RoomReservationSystem.dto.TicketDTO;
import static RoomReservationSystem.dto.TicketDTO.toTicketDTOList;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.TicketRepository;
import RoomReservationSystem.service.TicketService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService { 
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Override
    public Ticket save(Reservation reservation, User user) {
        return ticketRepository.save(new Ticket(
                Status.PENDING,
                new Date(),
                TICKET_CREATION_MESSAGE,
                user,
                reservation
        ));
    }
    
    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
    
    @Override
    public Ticket findById(int id) {
        return ticketRepository.findById(id);
    }
    
    @Override
    public Ticket findByReservation(Reservation res) {
        return ticketRepository.findByReservation(res);
    }
    
    @Override
    public List<TicketDTO> getAll() {
        return toTicketDTOList(ticketRepository.findAll());
    }
    
    @Override
    public List<TicketDTO> findByStatus(Status status) {
        List<Ticket> tickets = ticketRepository.findByStatus(status);
        return toTicketDTOList(tickets);
    }
    
    @Override
    public List<Ticket> findByStatusHelper(Status status) {
        return ticketRepository.findByStatus(status);
    }
    
    @Override
    public List<TicketDTO> findByUsername(String username) {
        User user = userService.findByUsername(username);
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return toTicketDTOList(tickets);
    }
    
    @Override
    public Ticket setStatus(int id, String status) {
        Ticket ticket = ticketRepository.findById(id);
        if (ticket != null) {
            delete(ticket);
            ticket.setStatus(Status.valueOf(status));
            ticket.setLastModification(new Date());
            
            return ticketRepository.save(ticket);
        }
        else {
            return null;
        }
    }
    
}
