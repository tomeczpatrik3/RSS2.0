package RoomReservationSystem.api;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.service.impl.ClassroomServiceImpl;
import RoomReservationSystem.validation.ClassroomValidator;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/classroom")
public class ClassroomApiController {
    
    @Autowired
    ClassroomServiceImpl classroomService;
    
    @Autowired
    ClassroomValidator classroomValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<ClassroomDTO> getAll(){
        return classroomService.findAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return classroomService.getNames();
    }  
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public Classroom findByName(@PathVariable String name){
	return classroomService.findByName(name);
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getNamesByBuilding/{name}")
    public List<String> getNamesByBuilding(@PathVariable String name){
	return classroomService.getNamesByBuilding(name);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasPC/{hasPC}")
    public List<Classroom> findByHasPC(@PathVariable boolean hasPC){
	return classroomService.findByHasPc(hasPC);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasProjector/{hasProjector}")
    public List<Classroom> findByHasProjector(@PathVariable boolean hasProjector){
	return classroomService.findByHasProjector(hasProjector);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsLessThan/{chair}")
    public List<Classroom> findByChairsLessThan(@PathVariable int chairs){
	return classroomService.findByChairsLessThan(chairs);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsGreaterThan/{chair}")
    public List<Classroom> findByChairsGreaterThan(@PathVariable int chairs){
	return classroomService.findByChairsGreaterThan(chairs);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsBetween")
    public List<Classroom> findByChairsBetween(@PathVariable int from, @PathVariable int to){
	return classroomService.findByChairsBetween(from, to);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByRoomName/{name}")
    public ResponseEntity<Classroom> deleteByRoomName(@PathVariable String name){
	classroomService.deleteByName(name);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createClassroom")
    public ResponseEntity createClassroom(@RequestBody ClassroomDTO cr, BindingResult bindingResult) {
        classroomValidator.validate(cr, bindingResult);
        if ( !bindingResult.hasErrors() ) {
            Classroom saved = classroomService.save(cr);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateClassroom")
    public ResponseEntity updateClassroom(@RequestBody ClassroomDTO cr, BindingResult bindingResult) {
        classroomValidator.validate(cr, bindingResult);
        //Ha csak az a hiba hogy már szerepel ilyen tanterem:
        if (bindingResult.hasErrors() && 
                bindingResult.getErrorCount()==1 && 
                bindingResult.getFieldError("name") != null) {
            classroomService.delete( classroomService.findByName(cr.getName()));
            Classroom saved = classroomService.save(cr);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);         
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    } 
}


