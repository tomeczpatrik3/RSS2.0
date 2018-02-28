package RoomReservationSystem.api;

import RoomReservationSystem.model.Subject;
import RoomReservationSystem.service.impl.SubjectServiceImpl;
import RoomReservationSystem.validation.SubjectValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    
    @GetMapping
    public Iterable<Subject> getAll(){
        return subjectService.findAll();
    }
    
    
    @GetMapping("/getSubjectNames")
    public List<String> getSubjectNames(){
        return subjectService.getSubjectNames();
    }
    
    
    @PostMapping("/createSubject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject, BindingResult bindingResult) {
        subjectValidator.validate(subject, bindingResult);
        if (!bindingResult.hasErrors()) {
            Subject saved = subjectService.save(subject);
            return ResponseEntity.ok(saved);            
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }          
    }
    
    @PostMapping("/updateSubject")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject, BindingResult bindingResult) {
        subjectValidator.validate(subject, bindingResult);
        if (!bindingResult.hasErrors()) {
            subjectService.delete( subjectService.findByName(subject.getName()) );
            Subject saved = subjectService.save(subject);
            return ResponseEntity.ok(saved);            
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    
    
    @PostMapping("/deleteByName")
    public ResponseEntity<Subject> deleteByName(@RequestParam String name) {
        if ( subjectService.findByName( name ) != null ) {
            subjectService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);           
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }  
}
