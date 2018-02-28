package RoomReservationSystem.api;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.service.impl.ReservationServiceImpl;
import RoomReservationSystem.validation.ReservationValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/reservation")
public class ReservationApiController {
    
    @Autowired
    ReservationServiceImpl reservationService;
    
    @Autowired
    ReservationValidator reservationValidator;
    
    @GetMapping
    public Iterable<Reservation> getAll(){
        return reservationService.findAll();
    }
    
    @PostMapping("/createRes")
    public ResponseEntity<Reservation> createRes(@RequestBody Reservation res, BindingResult bindingResult) {
        reservationValidator.validate(res, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(res);
            return ResponseEntity.ok(saved);            
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        
    }
    
}
