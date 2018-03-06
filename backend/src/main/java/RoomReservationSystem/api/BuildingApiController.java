package RoomReservationSystem.api;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.BUILDING_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.service.impl.BuildingServiceImpl;
import RoomReservationSystem.validation.BuildingValidator;
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
@RequestMapping(value="/api/building")
public class BuildingApiController {
    
    @Autowired
    BuildingServiceImpl buildingService;
    
    @Autowired
    BuildingValidator buildingValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public Iterable<Building> getAll(){
        return buildingService.findAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return buildingService.getNames();
    }  
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public Building findByName(@PathVariable String name){
	return buildingService.findByName(name);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}")
    public Building findById(@PathVariable int id){
	return buildingService.findById(id);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByName/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
	if ( buildingService.findByName( name ) != null ) {
            buildingService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);            
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BUILDING_NOT_EXISTS);
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createBuilding")
    public ResponseEntity createClassroom(@RequestBody BuildingDTO building, BindingResult bindingResult) {
        buildingValidator.validate(building, bindingResult);
        if ( !bindingResult.hasErrors() ) {
            Building saved = buildingService.save(building);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    /*
    @PostMapping("/updateClassroom")
    public ResponseEntity<Building> updateClassroom(@RequestBody Classroom cr, BindingResult bindingResult) {
        classroomValidator.validate(cr, bindingResult);
        if (!bindingResult.hasErrors()) {
            classroomService.delete( classroomService.findByRoomName(cr.getRoomName()));
            Classroom saved = classroomService.save(cr);
            return ResponseEntity.ok(saved);          
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    } 
    */
}
