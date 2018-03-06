package RoomReservationSystem.api;

import static RoomReservationSystem.config.ApiErrorMessageConstants.ERROR_RESERVATION_CREATE;
import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.service.impl.ReservationServiceImpl;
import RoomReservationSystem.validation.ReservationValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/reservation")
public class ReservationApiController {
    
    @Autowired
    ReservationServiceImpl reservationService;
    
    @Autowired
    ReservationValidator reservationValidator;
    
    @GetMapping
    public List<ReservationDTO> getAll(){
        return reservationService.getAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createRes")
    public ResponseEntity createRes(@RequestBody ReservationDTO res, BindingResult bindingResult) {
        reservationValidator.validate(res, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(res);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ERROR_RESERVATION_CREATE);
        }
        
    }
    
}
