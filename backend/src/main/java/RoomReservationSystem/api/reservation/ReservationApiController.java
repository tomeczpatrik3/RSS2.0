package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.service.ReservationService;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTOList;
import static RoomReservationSystem.config.ErrorMessageConstants.RESERVATION_NOT_EXISTS;
import static RoomReservationSystem.config.ErrorMessageConstants.STATUS_NOT_EXISTS;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.TypeService;
import static RoomReservationSystem.util.DateUtils.getDate;
import RoomReservationSystem.validation.BaseReservationValidator;
import RoomReservationSystem.validation.EventReservationValidator;
import RoomReservationSystem.validation.SemesterReservationValidator;
import RoomReservationSystem.validation.SimpleReservationValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A foglalásokhoz tartozó végpontokat tartalmazó ősosztály
 * @author Tomecz Patrik
 */
public abstract class ReservationApiController {
    
    @Autowired
    ReservationService reservationService;
    
    @Autowired
    StatusService statusService;
    
    @Autowired
    TypeService typeService;
    
    /*Validátorok*/
    @Autowired
    BaseReservationValidator baseReservationValidator;
    
    @Autowired
    SimpleReservationValidator simpleReservationValidator;
    
    @Autowired
    SemesterReservationValidator semesterReservationValidator;
    
    @Autowired
    EventReservationValidator eventReservationValidator;
    
    /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     * @return A megfelelő foglalások egy listában
     */
    @GetMapping
    public List<ReservationDTO> getAccepted(){
        return toReservationDTOList(reservationService.findByStatus("ACCEPTED"));
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByUsername/{username}")
    public List<ReservationDTO> findByUsername(@PathVariable String username){
	return toReservationDTOList(reservationService.findByUsername(username));
    }
    
    /**
     * 
     * @param status
     * @return 
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByStatus/{status}")
    public ResponseEntity findByStatus(@PathVariable String status){
        if (statusService.findByName(status) != null)
            return ResponseEntity.status(HttpStatus.OK).body(toReservationDTOList(reservationService.findByStatus(status)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(STATUS_NOT_EXISTS);  
    } 
    
    /**
     * 
     * @param building
     * @param classroom
     * @param date
     * @return 
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByClassroomAndDate")
    public List<ReservationDTO> findByClassroomAndDate(
            @RequestParam("building") String building,
            @RequestParam("classroom") String classroom,
            @RequestParam("date") String date
    ){
	return toReservationDTOList(
                reservationService.findByClassroomAndDate(
                        building,
                        classroom,
                        getDate(date)
                ));
    }
    
    /**
     * 
     * @param id
     * @param status
     * @return 
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/setStatus")
    public ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status){
	if (reservationService.findById(id) != null)
            return ResponseEntity.ok(toReservationDTO(reservationService.setStatus(id, status)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RESERVATION_NOT_EXISTS);
    }
    
//    /**
//     * 
//     * @param type
//     * @return 
//     */
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/findByType")
//    public ResponseEntity findByType(@RequestParam("type") String type){
//	Type reservationType = typeService.findByName(type.toUpperCase());
//        if ( reservationType != null)
//            return ResponseEntity.ok(toReservationDTOList(reservationService.findByType(reservationType)));
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TYPE_NOT_EXISTS);
//    }    
  
//    /**
//     * 
//     * @param status
//     * @param type
//     * @return 
//     */
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/findByStatusAndType")
//    public ResponseEntity findByStatusAndType(@RequestParam("status") String status, @RequestParam("type") String type){
//	Status reservationStatus = statusService.findByName(status);
//        Type reservationType = typeService.findByName(type.toUpperCase());
//        if ( reservationType != null && reservationStatus!= null)
//            return ResponseEntity.ok(toReservationDTOList(reservationService.findByStatusAndType(reservationStatus, reservationType)));
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TYPE_OR_STATUS_NOT_EXISTS);
//    }   
    
//    /**
//     * 
//     * @param simpleReservationDTO
//     * @param bindingResult
//     * @return 
//     */
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @PostMapping("/createSimpleReservation")
//    public ResponseEntity createSimpleReservation(@RequestBody SimpleReservationDTO simpleReservationDTO, BindingResult bindingResult) {
//        baseReservationValidator.validate(simpleReservationDTO, bindingResult);
//        simpleReservationValidator.validate(simpleReservationDTO, bindingResult);
//        if (!bindingResult.hasErrors()) {
//            Reservation saved = reservationService.save(simpleReservationDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
//        }        
//    }
//    
//    /**
//     * 
//     * @param semesterReservationDTO
//     * @param bindingResult
//     * @return 
//     */
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @PostMapping("/createSemesterReservation")
//    public ResponseEntity createSemesterReservation(@RequestBody SemesterReservationDTO semesterReservationDTO, BindingResult bindingResult) {
//        baseReservationValidator.validate(semesterReservationDTO, bindingResult);
//        semesterReservationValidator.validate(semesterReservationDTO, bindingResult);
//        if (!bindingResult.hasErrors()) {
//            Reservation saved = reservationService.save(semesterReservationDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(toReservationDTO(saved));           
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
//        }        
//    }
//    

}
