package RoomReservationSystem.dto;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private int id;
    private String status;
    private String lastModification;
    private String message;
    
    public static TicketDTO toTicketDTO(Ticket ticket, User user, Reservation reservation) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getStatus().toString(),
                ticket.getLastModification().toString(),
                ticket.getMessage()
        );
    }
  
    public static List<TicketDTO> toTicketDTOList(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        
        for (Ticket ticket: tickets) {
            ticketDTOs.add(toTicketDTO(
                    ticket,
                    ticket.getUser(),
                    ticket.getReservation()
            ));     
        }
        
        return ticketDTOs;
    }
}
