package RoomReservationSystem.api;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.validation.ClassroomValidator;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTOList;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomAlredyExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import static RoomReservationSystem.model.Classroom.toClassroom;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A termekhez tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/classroom")
public class ClassroomApiController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ClassroomValidator classroomValidator;

    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található
     * termet
     *
     * @return A termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findAll()));
    }

    /**
     * A függvény ami visszaadja egy listában az adott névvel rendelkező
     * termeket
     *
     * @param name A név
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByName(name)));
    }

    /**
     * A függvény ami visszaadja az adott épülethez tartozó termeket
     *
     * @param buildingName Az épület neve
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByBuildingName/{buildingName}")
    public ResponseEntity findByBuildingName(@PathVariable String buildingName) {
        try {
            return ResponseEntity.ok(toClassroomDTOList(classroomService.findByBuildingName(buildingName)));
        } catch (BuildingNotExistsException ex) {
            return handleException(ex);
        }
    }
    
    /**
     * A függvény ami visszaadja az adott épülethez és névhez tartozó tantermet
     * @param name A terem neve
     * @param buildingName Az épület neve
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findByNameAndBuildingName")
    public ResponseEntity findByNameAndBuildingName(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "buildingName", required = true) String buildingName) {
        try {
            return ResponseEntity.ok(toClassroomDTO(classroomService.findByNameAndBuildingName(name, buildingName)));
        } catch (ClassroomNotExistsException | BuildingNotExistsException ex) {
            return handleException(ex);
        }
    }    

    /**
     * A függvény ami visszaadja a PC-vel rendelkező (vagy nem rendelkező)
     * termeket
     *
     * @param hasPC Van-e PC
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasPC/{hasPC}")
    public ResponseEntity findByHasPC(@PathVariable boolean hasPC) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByHasPc(hasPC)));
    }

    /**
     * A függvény ami visszaadja a projektorral rendelkező (vagy nem rendelkező)
     * termeket
     *
     * @param hasProjector Van-e projektor
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByHasProjector/{hasProjector}")
    public ResponseEntity findByHasProjector(@PathVariable boolean hasProjector) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByHasProjector(hasProjector)));
    }

    /**
     * A függvény ami visszaadja egy adott számnál kevesebb székkel rendelkező
     * termeket
     *
     * @param chairs A székek száma
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsLessThan/{chairs}")
    public ResponseEntity findByChairsLessThan(@PathVariable int chairs) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByChairsLessThan(chairs)));
    }

    /**
     * A függvény ami visszaadja egy adott számnál több székkel rendelkező
     * termeket
     *
     * @param chairs A székek száma
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsGreaterThan/{chairs}")
    public ResponseEntity findByChairsGreaterThan(@PathVariable int chairs) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByChairsGreaterThan(chairs)));
    }

    /**
     * A függvény ami visszaadja két adott szám közötti székkel rendelkező
     * termeket
     *
     * @param from A székek számának alsó korlátja
     * @param to A székek számának felső korlátja
     * @return A megfelelő termek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByChairsBetween")
    public ResponseEntity findByChairsBetween(@RequestParam(value = "from", required = true) int from, @RequestParam(value = "to", required = true) int to) {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findByChairsBetween(from, to)));
    }

    /**
     * A függvény ami létrehozza a megfelelő termet
     *
     * @param classroomDTO A terem
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createClassroom")
    public ResponseEntity createClassroom(@RequestBody ClassroomDTO classroomDTO, BindingResult bindingResult) {
        classroomValidator.validate(classroomDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Classroom saved = classroomService.save(toClassroom(
                        classroomDTO,
                        buildingService.findByName(classroomDTO.getBuilding())
                ));
                return ResponseEntity.status(HttpStatus.CREATED).body(toClassroomDTO(saved));
            } catch (ClassroomAlredyExistsException | BuildingNotExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami törli az adott névhez és épülethez tartozó termet
     *
     * @param name A terem neve
     * @param buildingName Az épület neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/deleteByNameAndBuildingName")
    public ResponseEntity deleteByNameAndBuildingName(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "buildingName", required = true) String buildingName) {
        try {
            classroomService.deleteByNameAndBuildingName(name, buildingName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami firssíti a megfelelő termet
     *
     * @param classroomDTO A terem
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateClassroom")
    public ResponseEntity updateClassroom(@RequestBody ClassroomDTO classroomDTO, BindingResult bindingResult) {
        classroomValidator.validate(classroomDTO, bindingResult);
        if (bindingResult.hasErrors()
                && bindingResult.getErrorCount() == 1
                && bindingResult.getFieldError("name") != null) {

            try {
                classroomService.deleteByNameAndBuildingName(
                        classroomDTO.getName(),
                        classroomDTO.getBuilding()
                );

                Classroom saved = classroomService.save(toClassroom(
                        classroomDTO,
                        buildingService.findByName(classroomDTO.getBuilding())
                ));

                return ResponseEntity.status(HttpStatus.CREATED).body(toClassroomDTO(saved));
            } catch (ClassroomAlredyExistsException | ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
                return handleException(ex);
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
}
