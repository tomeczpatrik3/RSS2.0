package RoomReservationSystem.api;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.SUBJECT_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.service.impl.SubjectServiceImpl;
import RoomReservationSystem.validation.SubjectValidator;
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

@RestController
@RequestMapping(value="/api/subject")
public class SubjectApiController {
    
    @Autowired
    private SubjectServiceImpl subjectService;
    
    @Autowired
    private SubjectValidator subjectValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public Iterable<Subject> getAll(){
        return subjectService.findAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getSubjectNames")
    public List<String> getSubjectNames(){
        return subjectService.getSubjectNames();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createSubject")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subject, BindingResult bindingResult) {
        subjectValidator.validate(subject, bindingResult);
        if (!bindingResult.hasErrors()) {
            Subject saved = subjectService.save(subject);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }          
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateSubject")
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subject, BindingResult bindingResult) {
        subjectValidator.validate(subject, bindingResult);
        if (!bindingResult.hasErrors()) {
            subjectService.delete( subjectService.findByName(subject.getName()) );
            Subject saved = subjectService.save(subject);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);            
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByName")
    public ResponseEntity deleteByName(@RequestParam String name) {
        if ( subjectService.findByName( name ) != null ) {
            subjectService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);          
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SUBJECT_NOT_EXISTS);
        }
    }  
}
