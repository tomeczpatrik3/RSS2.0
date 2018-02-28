package RoomReservationSystem.api;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.service.impl.BuildingServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping
    public Iterable<Building> getAll(){
        return buildingService.findAll();
    }
    
    @GetMapping("/getBuildingNames")
    public List<String> getBuildingNames(){
        return buildingService.getBuildingNames();
    }  
    
    @GetMapping("/findByName/{name}")
    public Building findByName(@PathVariable String name){
	return buildingService.findByName(name);
    }
    
    @PostMapping("/deleteByName/{name}")
    public ResponseEntity<Building> deleteByName(@PathVariable String name){
	buildingService.deleteByName(name);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/createBuilding")
    public ResponseEntity<Building> createClassroom(@RequestBody Building building, BindingResult bindingResult) {
        Building saved = buildingService.save(building);
        return ResponseEntity.ok(saved);             
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
