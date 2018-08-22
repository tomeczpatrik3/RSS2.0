package RoomReservationSystem.api.reservation;

import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import RoomReservationSystem.dto.SemesterReservationDTO;
import RoomReservationSystem.model.reservation.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A szemeszterre vonatkozó foglalásokhoz tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/semesterReservation")
public class SemesterReservationApiController extends ReservationApiController {

    /**
     * A függvény ami létrehozza a megfelelő szemeszterre vonatkozó foglalást
     * @param semesterReservationDTO A foglalás
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody SemesterReservationDTO semesterReservationDTO, BindingResult bindingResult) {
        baseReservationValidator.validate(semesterReservationDTO, bindingResult);
        semesterReservationValidator.validate(semesterReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Reservation saved = reservationService.save(semesterReservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }        
    }
    
}
