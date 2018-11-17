package RoomReservationSystem.controller.api;

import RoomReservationSystem.dto.EventReservationDTO;
import static RoomReservationSystem.dto.EventReservationDTO.toEventReservationDTO;
import static RoomReservationSystem.dto.EventReservationDTO.toEventReservationDTOList;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.EventReservationAlredyExistsException;
import RoomReservationSystem.exception.EventReservationNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.EventReservation;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.EventReservationService;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;
import RoomReservationSystem.validation.BaseReservationValidator;
import RoomReservationSystem.validation.EventReservationValidator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Az esemény foglalásokhoz tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/eventReservation")
public class EventReservationApiController extends ReservationApiController {

    @Autowired
    EventReservationService eventService;

    @Autowired
    StatusService statusService;

    @Autowired
    BaseReservationValidator baseRValidator;

    @Autowired
    EventReservationValidator eventRValidator;

    /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     *
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/getAccepted")
    @Override
    public ResponseEntity getAccepted() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTOList(eventService.findByStatus("ACCEPTED")));
        } catch (StatusNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja a várakozó foglalásokat
     *
     * @return A megfelelő foglalások egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getPending")
    @Override
    public ResponseEntity getPending() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTOList(eventService.findByStatus("PENDING")));
        } catch (StatusNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található
     * eseményre vonatkozó foglalás nevét
     *
     * @return A nevek egy listában
     */
    @GetMapping("/getNames")
    public ResponseEntity getNames() {
        return ResponseEntity.ok(eventService.getNames());
    }

    /**
     * A függvény ami visszaadja az adott azonosítóhoz tartozó foglalásokat
     *
     * @param username A felhasználónév
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByUsername/{username}")
    @Override
    public ResponseEntity findByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTOList(eventService.findByUsername(username)));
        } catch (UserNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findById")
    @Override
    public ResponseEntity findById(@RequestParam(value = "id", required = true) int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTO(eventService.findById(id)));
        } catch (EventReservationNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott státusszal rendelkező foglalásokat
     *
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByStatus/{status}")
    @Override
    public ResponseEntity findByStatus(@PathVariable String status) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTOList(eventService.findByStatus(status)));
        } catch (StatusNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja egy adott épület adott tanterméhez tartozó
     * foglalásokat
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByBuildingAndClassroom")
    @Override
    public ResponseEntity findByBuildingAndClassroom(@RequestParam(value = "building", required = true) String building, @RequestParam(value = "classroom", required = true) String classroom) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toEventReservationDTOList(eventService.findByBuildingAndClassroom(building, classroom)));
        } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott névhez tartozó foglalást
     *
     * @param name A foglalás neve
     * @return A megfelelő foglalás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(value = "name", required = true) String name) {
        try {
            return ResponseEntity.ok(toEventReservationDTO(eventService.findByName(name)));
        } catch (EventReservationNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami beállítja egy adott foglalást státuszát a paraméterben
     * átadott értékre
     *
     * @param id A foglalás azonosítója
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/setStatus")
    @Override
    public ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        try {
            return ResponseEntity.ok(toEventReservationDTO(eventService.setStatus(id, status)));
        } catch (StatusNotExistsException | EventReservationNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami létrehozza a megfelelő eseményre vonatkozó foglalást
     *
     * @param eventReservationDTO A foglalás
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody EventReservationDTO eventReservationDTO, BindingResult bindingResult) {
        baseRValidator.validate(eventReservationDTO, bindingResult);
        eventRValidator.validate(eventReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                EventReservation saved = eventService.save(eventReservationDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(toEventReservationDTO(saved));
            } catch (UserNotExistsException | ClassroomNotExistsException | StatusNotExistsException | SemesterNotExistsException | BuildingNotExistsException | EventReservationAlredyExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A foglalások frissítéséért felelős függvény
     *
     * @param id Az azonosító
     * @param eventReservationDTO A foglalás
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody EventReservationDTO eventReservationDTO, BindingResult bindingResult) {
        baseRValidator.validate(eventReservationDTO, bindingResult);
        eventRValidator.validate(eventReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                EventReservation updated = eventService.update(id, eventReservationDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(toEventReservationDTO(updated));
            } catch (EventReservationNotExistsException | UserNotExistsException | ClassroomNotExistsException | StatusNotExistsException | SemesterNotExistsException | BuildingNotExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    /**
     * A saját foglalások frissítéséért felelős függvény
     *
     * @param id Az azonosító
     * @param eventReservationDTO A foglalás
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/updateOwnById/{id}")
    public ResponseEntity updateOwnById(@PathVariable int id, @RequestBody EventReservationDTO eventReservationDTO, BindingResult bindingResult) {
        baseRValidator.validate(eventReservationDTO, bindingResult);
        eventRValidator.validate(eventReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                EventReservation updated = eventService.updateOwnById(id, eventReservationDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(toEventReservationDTO(updated));
            } catch (EventReservationNotExistsException | ClassroomNotExistsException | StatusNotExistsException | SemesterNotExistsException | BuildingNotExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami törli az adott azonosítóhoz tartozó foglalásokat
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteById")
    @Override
    public ResponseEntity deleteById(@RequestParam(value = "id", required = true) int id) {
        try {
            eventService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EventReservationNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott felhasználóhoz tartozó foglalásokat
     *
     * @param username A felhasználó neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByUsername")
    @Override
    public ResponseEntity deleteByUsername(@RequestParam(value = "username", required = true) String username) {
        try {
            eventService.deleteByUsername(username);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott épület adott tanterméhez tartozó
     * foglalásokat
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByBuildingAndClassroom")
    @Override
    public ResponseEntity deleteByBuildingAndClassroom(@RequestParam(value = "building", required = true) String building, @RequestParam(value = "classroom", required = true) String classroom) {
        try {
            eventService.deleteByBuildingAndClassroom(building, classroom);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott státuszhoz tartozó foglalásokat
     *
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByStatus")
    @Override
    public ResponseEntity deleteByStatus(@RequestParam(value = "status", required = true) String status) {
        try {
            eventService.deleteByStatus(status);
            return new ResponseEntity(HttpStatus.OK);
        } catch (StatusNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott névhez tartozó foglalást
     *
     * @param name A név
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByName")
    public ResponseEntity deleteByName(@RequestParam(value = "name", required = true) String name) {
        try {
            eventService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EventReservationNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja, hogy létezik-e az adott azonosítóhoz tartozó
     * entitás
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsById")
    public ResponseEntity existsById(@RequestParam(value = "id", required = true) int id) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.existsById(id));
    }

    /**
     * A függvény ami visszaadja, hogy létezik-e az adott névhez tartozó entitás
     *
     * @param name Az esemény neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsByName")
    public ResponseEntity existsByName(@RequestParam(value = "name", required = true) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.existsByName(name));
    }
}
