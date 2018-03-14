package RoomReservationSystem.api;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.SUBJECT_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import RoomReservationSystem.dto.SemesterDTO;
import static RoomReservationSystem.dto.SemesterDTO.toSemesterDTO;
import static RoomReservationSystem.dto.SemesterDTO.toSemesterDTOList;
import RoomReservationSystem.model.Semester;
import static RoomReservationSystem.model.Semester.toSemester;
import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.validation.SemesterValidator;
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
@RequestMapping(value="/api/semester")
public class SemesterApiController {
    
    @Autowired
    private SemesterService semesterService;
    
    @Autowired
    private SemesterValidator semesterValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<SemesterDTO> getAll(){
        return toSemesterDTOList(semesterService.getAll());
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getSemesterNames")
    public List<String> getSemesterNames(){
        return semesterService.getNames();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createSemester")
    public ResponseEntity createSemester(@RequestBody SemesterDTO semesterDTO, BindingResult bindingResult) {
        semesterValidator.validate(semesterDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            Semester saved = semesterService.save(toSemester(semesterDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(toSemesterDTO(saved));           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }          
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByName")
    public ResponseEntity deleteByName(@RequestParam String name) {
        if ( semesterService.findByName( name ) != null ) {
            semesterService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);          
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SUBJECT_NOT_EXISTS);
        }
    }  
}

