package RoomReservationSystem.controller.api;

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
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(toSubjectDTOList(subjectService.findAll()));
    }

    /**
     * A függvény ami visszaadja egy listában a tantárgyak neveit
     *
     * @return A tantárgyak nevei egy listában
     */
    @GetMapping("/getSubjectNames")
    public ResponseEntity getSubjectNames() {
        return ResponseEntity.ok(subjectService.getSubjectNames());
    }

    /**
     * A függvény ami visszaadja az adott tárgykódhoz tartozó tantárgy nevét
     *
     * @param subjectCode A tárgykód
     * @return A megfelelő válaszentitás
     */
    @GetMapping("/getSubjectName")
    public ResponseEntity getSubjectName(@RequestParam(value = "subjectCode", required = true) String subjectCode) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectName(subjectCode));
        } catch (SubjectNotExistsException ex) {
            return handleException(ex);
        }
    }
    
    /**
     * A függvény ami visszaadja az adott azonosítóval rendelkező tantárgyat
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity findByName(@PathVariable int id) {
        try {
            return ResponseEntity.ok(toSubjectDTO(subjectService.findById(id)));
        } catch (SubjectNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami létrehozza a megfelelő tantárgyat
     *
     * @param subjectDTO A tantárgy
     * @param bindingResult A BindingResult objektum
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
     * A tantárgyak frissítéséért felelős függvény
     *
     * @param id Az azonosító
     * @param subjectDTO A tantárgy
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody SubjectDTO subjectDTO, BindingResult bindingResult) {
        subjectValidator.validate(subjectDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Subject updated = subjectService.update(
                        id,
                        toSubject(subjectDTO)
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(toSubjectDTO(updated));
            } catch (SubjectNotExistsException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami törli az adott kódhoz tartozó tantárgyat
     *
     * @param subjectCode A tárgykód
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByCode")
    public ResponseEntity deleteByCode(@RequestParam(value = "subjectCode", required = true) String subjectCode) {
        try {
            subjectService.deleteByCode(subjectCode);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SubjectNotExistsException | NullPointerException ex) {
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
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.existsById(id));
    }

    /**
     * A függvény ami visszaadja, hogy létezik-e az adott tárgykódhoz tartozó
     * entitás
     *
     * @param code A tárgykód
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsByCode")
    public ResponseEntity existsByCode(@RequestParam(value = "code", required = true) String code) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.existsByCode(code));
    }
}
