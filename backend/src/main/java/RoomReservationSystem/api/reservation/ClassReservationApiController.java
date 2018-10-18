package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.reservation.ClassReservationDTO;
import static RoomReservationSystem.dto.reservation.ClassReservationDTO.toClassReservationDTO;
import static RoomReservationSystem.dto.reservation.ClassReservationDTO.toClassReservationDTOList;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassReservationNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.service.StatusService;
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
import RoomReservationSystem.service.reservation.ClassReservationService;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;
import RoomReservationSystem.validation.BaseReservationValidator;
import RoomReservationSystem.validation.ClassReservationValidator;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * A tanórákra vonatkozó foglalásokhoz tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/classReservation")
public class ClassReservationApiController extends ReservationApiController {

    @Autowired
    ClassReservationService classService;

    @Autowired
    StatusService statusService;

    @Autowired
    BaseReservationValidator baseRValidator;

    @Autowired
    ClassReservationValidator classRValidator;

    /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     *
     * @return A megfelelő foglalások egy listában
     */
    @GetMapping("/getAccepted")
    @Override
    public ResponseEntity getAccepted() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findByStatus("ACCEPTED")));
        } catch (StatusNotExistsException | NullPointerException ex) {
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
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTO(classService.findById(id)));
        } catch (ClassReservationNotExistsException ex) {
            return handleException(ex);
        }

    }

    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     *
     * @param username A felhasználónév
     * @return A megfelelő foglalások egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByUsername/{username}")
    @Override
    public ResponseEntity findByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findByUsername(username)));
        } catch (UserNotExistsException | NullPointerException ex) {
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
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findByStatus(status)));
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
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findByBuildingAndClassroom(building, classroom)));
        } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja egy adott tantárgyhoz tartozó foglalásokat
     *
     * @param subjectCode A tárgykód
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findBySubjectCode")
    public ResponseEntity findBySubjectCode(@RequestParam(value = "subjectCode", required = true) String subjectCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findBySubjectCode(subjectCode)));
        } catch (SubjectNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja egy adott szemeszterhez tartozó foglalásokat
     *
     * @param semester A szemeszter neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findBySemester")
    public ResponseEntity findBySemester(@RequestParam(value = "semester", required = true) String semester) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(toClassReservationDTOList(classService.findBySemester(semester)));
        } catch (SemesterNotExistsException | NullPointerException ex) {
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
            return ResponseEntity.ok(toClassReservationDTO(classService.setStatus(id, status)));
        } catch (StatusNotExistsException | ClassReservationNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami létrehozza a megfelelő tanórára vonatkozó foglalást
     *
     * @param classReservationDTO A foglalás
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/createReservation")
    public ResponseEntity createReservation(@RequestBody ClassReservationDTO classReservationDTO, BindingResult bindingResult) {
        baseRValidator.validate(classReservationDTO, bindingResult);
        classRValidator.validate(classReservationDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                ClassReservation saved = classService.save(classReservationDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(toClassReservationDTO(saved));
            } catch (UserNotExistsException | SubjectNotExistsException | ClassroomNotExistsException | StatusNotExistsException | SemesterNotExistsException | BuildingNotExistsException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(concatErrors(bindingResult));
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
            classService.deleteByUsername(username);
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
            classService.deleteByBuildingAndClassroom(building, classroom);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott tantrágyhoz tartozó foglalásokat
     *
     * @param subjectCode A tárgykód
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteBySubjectCode")
    public ResponseEntity deleteBySubjectCode(@RequestParam(value = "subjectCode", required = true) String subjectCode) {
        try {
            classService.deleteBySubjectCode(subjectCode);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SubjectNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott szemeszterhez tartozó foglalásokat
     *
     * @param semester A szemeszter
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteBySemester")
    public ResponseEntity deleteBySemester(@RequestParam(value = "semester", required = true) String semester) {
        try {
            classService.deleteBySemester(semester);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SemesterNotExistsException | NullPointerException ex) {
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
            classService.deleteByStatus(status);
            return new ResponseEntity(HttpStatus.OK);
        } catch (StatusNotExistsException | NullPointerException ex) {
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
        return ResponseEntity.status(HttpStatus.OK).body(classService.existsById(id));
    }
}
