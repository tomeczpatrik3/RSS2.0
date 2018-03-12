package RoomReservationSystem.api;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.validation.ReservationValidator;
import RoomReservationSystem.service.ReservationService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTOList;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.RESERVATION_NOT_EXISTS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/reservation")
public class ReservationApiController {
    
    @Autowired
    ReservationService reservationService;
    
    @Autowired
    ReservationValidator reservationValidator;
    
    @GetMapping
    public List<ReservationDTO> getAccepted(){
        return toReservationDTOList(reservationService.findByStatus("ACCEPTED"));
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByUsername/{username}")
    public List<ReservationDTO> findByName(@PathVariable String username){
	return toReservationDTOList(reservationService.findByUsername(username));
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createRes")
    public ResponseEntity createRes(@RequestBody ReservationDTO reservationDTO, BindingResult bindingResult) {
        reservationValidator.validate(reservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(reservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
        
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByStatus/{status}")
    public List<ReservationDTO> findByStatus(@PathVariable String status){
	return toReservationDTOList(reservationService.findByStatus(status));
    } 
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/setStatus")
    public ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status){
	if (reservationService.findById(id) != null)
            return ResponseEntity.ok(toReservationDTO(reservationService.setStatus(id, status)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RESERVATION_NOT_EXISTS);
    } 
}
