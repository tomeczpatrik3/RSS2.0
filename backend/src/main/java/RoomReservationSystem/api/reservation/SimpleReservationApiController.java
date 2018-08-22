package RoomReservationSystem.api.reservation;

import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import RoomReservationSystem.dto.SimpleReservationDTO;
import RoomReservationSystem.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Az egyszeri foglalásokhoz tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/simpleReservation")
public class SimpleReservationApiController extends ReservationApiController {
    
    /**
     * A függvény ami létrehozza a megfelelő egyszerű foglalást
     * @param simpleReservationDTO A foglalás
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody SimpleReservationDTO simpleReservationDTO, BindingResult bindingResult) {
        baseReservationValidator.validate(simpleReservationDTO, bindingResult);
        simpleReservationValidator.validate(simpleReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(simpleReservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }        
    }
    

}
