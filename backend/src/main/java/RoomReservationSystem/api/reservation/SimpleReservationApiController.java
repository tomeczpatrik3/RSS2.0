package RoomReservationSystem.api.reservation;

import static RoomReservationSystem.config.ErrorMessageConstants.RESERVATION_NOT_EXISTS;
import static RoomReservationSystem.config.ErrorMessageConstants.STATUS_NOT_EXISTS;
import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;
import RoomReservationSystem.dto.reservation.SimpleReservationDTO;
import static RoomReservationSystem.dto.reservation.SimpleReservationDTO.toSimpleReservationDTO;
import static RoomReservationSystem.dto.reservation.SimpleReservationDTO.toSimpleReservationDTOList;
import RoomReservationSystem.model.reservation.SimpleReservation;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.reservation.SimpleReservationService;
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

/**
 * Az egyszeri foglalásokhoz tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/simpleReservation")
public class SimpleReservationApiController extends ReservationApiController {
    @Autowired
    SimpleReservationService simpleService;
    
    @Autowired
    StatusService statusService;
    
    /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     * @return A megfelelő foglalások egy listában
     */
    @GetMapping
    @Override
    public List<SimpleReservationDTO> getAccepted(){
        return toSimpleReservationDTOList(simpleService.findByStatus("ACCEPTED"));
    }
    
    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     * @param username A felhasználónév
     * @return A megfelelő foglalások egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByUsername/{username}")
    @Override
    public List<SimpleReservationDTO> findByUsername(@PathVariable String username){
	return toSimpleReservationDTOList(simpleService.findByUsername(username));
    }
    
    /**
     * A függvény ami visszaadja az adott státusszal rendelkező foglalásokat
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByStatus/{status}")
    @Override
    public ResponseEntity findByStatus(@PathVariable String status){
        if (statusService.findByName(status) != null)
            return ResponseEntity.status(HttpStatus.OK).body(toSimpleReservationDTOList(simpleService.findByStatus(status)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(STATUS_NOT_EXISTS);  
    } 
    
    /**
     * A függvény ami visszaadja az adott terem kiválasztott időpontjára vonatkozó foglalásokat
     * @param building Az épület
     * @param classroom A terem
     * @param date  A dátum
     * @return A megfelelő foglalások egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByClassroomAndDate")
    @Override
    public List<SimpleReservationDTO> findByClassroomAndDate(
            @RequestParam("building") String building,
            @RequestParam("classroom") String classroom,
            @RequestParam("date") String date
    ){
//	return toReservationDTOList(
//                eventService.findByClassroomAndDate(
//                        building,
//                        classroom,
//                        getDate(date)
//                ));
    return null;
    }
    
    /**
     * A függvény ami beállítja egy adott foglalást státuszát a paraméterben átadott értékre
     * @param id A foglalás azonosítója
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/setStatus")
    @Override
    public ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status){
	if (simpleService.findById(id) != null)
            return ResponseEntity.ok(toSimpleReservationDTO(simpleService.setStatus(id, status)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RESERVATION_NOT_EXISTS);
    }
    
    /**
     * A függvény ami létrehozza a megfelelő egyszerű foglalást
     * @param simpleReservationDTO A foglalás
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody SimpleReservationDTO simpleReservationDTO, BindingResult bindingResult) {
//        baseReservationValidator.validate(simpleReservationDTO, bindingResult);
//        simpleReservationValidator.validate(simpleReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            SimpleReservation saved = simpleService.save(simpleReservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toSimpleReservationDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }        
    }
    
}
