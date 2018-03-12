package RoomReservationSystem.api;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.validation.BuildingValidator;
import RoomReservationSystem.service.BuildingService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.BUILDING_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTOList;
import static RoomReservationSystem.model.Building.toBuilding;

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
    private BuildingService buildingService;
    
    @Autowired
    private BuildingValidator buildingValidator;
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<BuildingDTO> getAll(){
        return toBuildingDTOList(buildingService.findAll());
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return buildingService.getNames();
    }  
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name){
	if (buildingService.findByName(name) != null)
            return ResponseEntity.ok(toBuildingDTO(buildingService.findByName(name)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BUILDING_NOT_EXISTS);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable int id){
	if (buildingService.findById(id) != null)
            return ResponseEntity.ok(toBuildingDTO(buildingService.findById(id)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BUILDING_NOT_EXISTS);  
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByName/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
	if ( buildingService.findByName( name ) != null ) {
            buildingService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);            
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BUILDING_NOT_EXISTS);
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createBuilding")
    public ResponseEntity createBuilding(@RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        buildingValidator.validate(buildingDTO, bindingResult);
        if ( !bindingResult.hasErrors() ) {
            Building saved = buildingService.save(
                    toBuilding(buildingDTO)
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(toBuildingDTO(saved));
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
