package RoomReservationSystem.api;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.validation.SubjectValidator;
import static RoomReservationSystem.model.Subject.toSubject;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTOList;
import static RoomReservationSystem.config.ErrorMessageConstants.SUBJECT_NOT_EXISTS;
import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;

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
    private SubjectService subjectService;
    
    @Autowired
    private SubjectValidator subjectValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<SubjectDTO> getAll(){
        return toSubjectDTOList(subjectService.findAll());
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getSubjectNames")
    public List<String> getSubjectNames(){
        return subjectService.getSubjectNames();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createSubject")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subjectDTO, BindingResult bindingResult) {
        subjectValidator.validate(subjectDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Subject saved = subjectService.save(toSubject(subjectDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(toSubjectDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }          
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateSubject")
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subjectDTO, BindingResult bindingResult) {
        subjectValidator.validate(subjectDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            subjectService.delete( subjectService.findByCode(subjectDTO.getCode()) );
            Subject saved = subjectService.save(toSubject(subjectDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(toSubjectDTO(saved));            
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByCode")
    public ResponseEntity deleteByCode(@RequestParam String code) {
        if ( subjectService.findByCode( code ) != null ) {
            subjectService.deleteByCode(code);
            return new ResponseEntity(HttpStatus.OK);          
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SUBJECT_NOT_EXISTS);
        }
    }  
}
