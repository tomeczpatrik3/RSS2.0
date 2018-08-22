package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.EventReservationDTO;
import RoomReservationSystem.model.Reservation;

import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Az esemény foglalásokhoz tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/eventReservation")
public class EventReservationApiController extends ReservationApiController {

    /**
     * A függvény ami létrehozza a megfelelő eseményre vonatkozó foglalást
     * @param eventReservationDTO A foglalás
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody EventReservationDTO eventReservationDTO, BindingResult bindingResult) {
        baseReservationValidator.validate(eventReservationDTO, bindingResult);
        eventReservationValidator.validate(eventReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(eventReservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        } 
    }
}
