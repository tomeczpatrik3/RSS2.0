package RoomReservationSystem.api;


import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.validation.ClassroomValidator;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import static RoomReservationSystem.config.ErrorMessageConstants.CLASSROOM_NOT_EXISTS;
import static RoomReservationSystem.config.ErrorMessageConstants.concatErrors;
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

/**
 * A termekhez tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/classroom")
public class ClassroomApiController {
    
    @Autowired
    private ClassroomService classroomService;
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private ClassroomValidator classroomValidator;
    
    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található termet
     * @return A termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<ClassroomDTO> getAll(){
        return toClassroomDTOList(classroomService.findAll());
    }
    
    /**
     * A függvény ami visszaadja egy listában az adott névvel rendelkező termeket
     * @param name A név
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public List<ClassroomDTO> findByName(@PathVariable String name){
	return toClassroomDTOList(classroomService.findByName(name));
    }
    
    /**
     * A függvény ami visszaadja az adott épülethez tartozó termeket
     * @param buildingName Az épület neve
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByBuildingName/{buildingName}")
    public List<Classroom> findByBuildingName(@PathVariable String buildingName){
	return classroomService.findByBuildingName(buildingName);
    }
    
    /**
     * A függvény ami visszaadja a PC-vel rendelkező (vagy nem rendelkező) termeket
     * @param hasPC Van-e PC
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasPC/{hasPC}")
    public List<ClassroomDTO> findByHasPC(@PathVariable boolean hasPC){
	return toClassroomDTOList(classroomService.findByHasPc(hasPC));
    }
    
    /**
     * A függvény ami visszaadja a projektorral rendelkező (vagy nem rendelkező) termeket
     * @param hasProjector Van-e projektor
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasProjector/{hasProjector}")
    public List<ClassroomDTO> findByHasProjector(@PathVariable boolean hasProjector){
	return toClassroomDTOList(classroomService.findByHasProjector(hasProjector));
    }
    
    /**
     * A függvény ami visszaadja egy adott számnál kevesebb székkel rendelkező termeket
     * @param chairs A székek száma
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsLessThan/{chair}")
    public List<ClassroomDTO> findByChairsLessThan(@PathVariable int chairs){
	return toClassroomDTOList(classroomService.findByChairsLessThan(chairs));
    }
    
    /**
     * A függvény ami visszaadja egy adott számnál több székkel rendelkező termeket
     * @param chairs A székek száma
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsGreaterThan/{chair}")
    public List<ClassroomDTO> findByChairsGreaterThan(@PathVariable int chairs){
	return toClassroomDTOList(classroomService.findByChairsGreaterThan(chairs));
    }
    
    /**
     * A függvény ami visszaadja két adott szám közötti székkel rendelkező termeket
     * @param from A székek számának alsó korlátja
     * @param to A székek számának felső korlátja
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsBetween")
    public List<ClassroomDTO> findByChairsBetween(@PathVariable int from, @PathVariable int to){
	return toClassroomDTOList(classroomService.findByChairsBetween(from, to));
    }
    
    /**
     * A függvény ami létrehozza a megfelelő termet
     * @param classroomDTO A terem
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
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
    
    /**
     * A függvény ami törli az adott névhez tartozó termet
     * @param name A terem neve
     * @return A megfelelő válasz entitás
     */
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
    
    /**
     * A függvény ami firssíti a megfelelő termet
     * @param classroomDTO A terem
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
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


