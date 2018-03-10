package RoomReservationSystem.api;

import RoomReservationSystem.dto.TicketDTO;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.service.impl.TicketServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/ticket")
public class TicketApiController {
    
    @Autowired
    TicketServiceImpl ticketService;
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<TicketDTO> getAll(){
        return ticketService.getAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByStatus/{status}")
    public List<TicketDTO> findByStatus(@PathVariable Status status){
	return ticketService.findByStatus(status);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByUsername/{username}")
    public List<TicketDTO> findByUsername(@PathVariable String username){
	return ticketService.findByUsername(username);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/setStatus")
    public ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        Ticket modified = ticketService.setStatus(id, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(modified);                
    }
    
}

