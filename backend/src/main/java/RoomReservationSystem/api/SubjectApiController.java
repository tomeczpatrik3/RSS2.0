package RoomReservationSystem.api;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.validation.SubjectValidator;
import static RoomReservationSystem.model.Subject.toSubject;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTOList;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;
import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A tantárgyakhoz tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/subject")
public class SubjectApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectValidator subjectValidator;

    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található
     * tantárgyat
     *
     * @return A tantárgyak egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(toSubjectDTOList(subjectService.findAll()));
    }

    /**
     * A függvény ami visszaadja egy listában a tantárgyak neveit
     *
     * @return A tantárgyak nevei egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getSubjectNames")
    public ResponseEntity getSubjectNames() {
        return ResponseEntity.ok(subjectService.getSubjectNames());
    }

    /**
     * A függvény ami létrehozza a megfelelő tantárgyat
     *
     * @param subjectDTO A tantárgy
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createSubject")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subjectDTO, BindingResult bindingResult) {
        subjectValidator.validate(subjectDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Subject saved = subjectService.save(toSubject(subjectDTO));
                return ResponseEntity.status(HttpStatus.CREATED).body(toSubjectDTO(saved));
            } catch (SubjectAlredyExistsException | NullPointerException ex) {
                return handleException(ex);
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami firssíti a megfelelő tantárgyat
     *
     * @param subjectDTO A tantárgy
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateSubject")
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subjectDTO, BindingResult bindingResult) {
        subjectValidator.validate(subjectDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                subjectService.deleteByCode(subjectDTO.getCode());
                Subject saved = subjectService.save(toSubject(subjectDTO));
                return ResponseEntity.status(HttpStatus.CREATED).body(toSubjectDTO(saved));
            } catch (SubjectNotExistsException | SubjectAlredyExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami törli az adott kódhoz tartozó tantárgyat
     *
     * @param code A tárgykód
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByCode")
    public ResponseEntity deleteByCode(@RequestParam String code) {
        try {
            subjectService.deleteByCode(code);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SubjectNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }
}
