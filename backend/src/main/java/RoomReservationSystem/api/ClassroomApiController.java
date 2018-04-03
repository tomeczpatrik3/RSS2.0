package RoomReservationSystem.api;


import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.validation.ClassroomValidator;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.CLASSROOM_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTOList;
import static RoomReservationSystem.model.Classroom.toClassroom;

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
    private ClassroomService classroomService;
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private ClassroomValidator classroomValidator;
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<ClassroomDTO> getAll(){
        return toClassroomDTOList(classroomService.findAll());
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public List<ClassroomDTO> findByName(@PathVariable String name){
	return toClassroomDTOList(classroomService.findByName(name));
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByBuildingName/{buildingName}")
    public List<Classroom> findByBuildingName(@PathVariable String buildingName){
	return classroomService.findByBuildingName(buildingName);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasPC/{hasPC}")
    public List<ClassroomDTO> findByHasPC(@PathVariable boolean hasPC){
	return toClassroomDTOList(classroomService.findByHasPc(hasPC));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasProjector/{hasProjector}")
    public List<ClassroomDTO> findByHasProjector(@PathVariable boolean hasProjector){
	return toClassroomDTOList(classroomService.findByHasProjector(hasProjector));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsLessThan/{chair}")
    public List<ClassroomDTO> findByChairsLessThan(@PathVariable int chairs){
	return toClassroomDTOList(classroomService.findByChairsLessThan(chairs));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsGreaterThan/{chair}")
    public List<ClassroomDTO> findByChairsGreaterThan(@PathVariable int chairs){
	return toClassroomDTOList(classroomService.findByChairsGreaterThan(chairs));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsBetween")
    public List<ClassroomDTO> findByChairsBetween(@PathVariable int from, @PathVariable int to){
	return toClassroomDTOList(classroomService.findByChairsBetween(from, to));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByRoomName/{name}")
    public ResponseEntity deleteByRoomName(@PathVariable String name){
	if (classroomService.findByName(name) != null) {
            classroomService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);            
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CLASSROOM_NOT_EXISTS);
        }

    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createClassroom")
    public ResponseEntity createClassroom(@RequestBody ClassroomDTO classroomDTO, BindingResult bindingResult) {
        classroomValidator.validate(classroomDTO, bindingResult);
        if ( !bindingResult.hasErrors() ) {
            Classroom saved = classroomService.save(toClassroom(
                    classroomDTO,
                    buildingService.findByName(classroomDTO.getBuilding())
            )); 
            return ResponseEntity.status(HttpStatus.CREATED).body(toClassroomDTO(saved));
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateClassroom")
    public ResponseEntity updateClassroom(@RequestBody ClassroomDTO classroomDTO, BindingResult bindingResult) {
        classroomValidator.validate(classroomDTO, bindingResult);
        if (bindingResult.hasErrors() && 
                bindingResult.getErrorCount()==1 && 
                bindingResult.getFieldError("name") != null) {
            
            classroomService.delete( classroomService.findByNameAndBuildingName(
                    classroomDTO.getName(),
                    classroomDTO.getBuilding()
            ));
            
            Classroom saved = classroomService.save(toClassroom(
                    classroomDTO,
                    buildingService.findByName(classroomDTO.getBuilding())
            ));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(toClassroomDTO(saved));         
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    } 
}


