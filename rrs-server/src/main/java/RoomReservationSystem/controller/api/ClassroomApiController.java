package RoomReservationSystem.controller.api;

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
import RoomReservationSystem.model.Building;
import static RoomReservationSystem.model.Classroom.toClassroom;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(toClassroomDTOList(classroomService.findAll()));
    }

    /**
     * A függvény ami visszaadja az adott azonosítóval rendelkező termet
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity findByName(@PathVariable int id) {
        try {
            return ResponseEntity.ok(toClassroomDTO(classroomService.findById(id)));
        } catch (ClassroomNotExistsException ex) {
            return handleException(ex);
        }
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
     *
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
     * A tantermek frissítéséért felelős függvény
     *
     * @param id Az azonosító
     * @param classroomDTO A tanterem
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody ClassroomDTO classroomDTO, BindingResult bindingResult) {
        classroomValidator.validate(classroomDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Classroom updated = classroomService.update(
                        id,
                        toClassroom(classroomDTO, buildingService.findByName(classroomDTO.getBuilding()))
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(toClassroomDTO(updated));
            } catch (ClassroomNotExistsException | BuildingNotExistsException | NullPointerException ex) {
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
    @DeleteMapping("/deleteByNameAndBuildingName")
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
     * A függvény ami visszaadja, hogy létezik-e az adott azonosítóhoz tartozó
     * entitás
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsById")
    public ResponseEntity existsById(@RequestParam(value = "id", required = true) int id) {
        return ResponseEntity.status(HttpStatus.OK).body(classroomService.existsById(id));
    }

    /**
     * A függvény ami visszaadja, hogy létezik-e az adott névhez és épülethez
     * tartozó entitás
     *
     * @param name A tanterem neve
     * @param building Az épület neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsByNameAndBuilding")
    public ResponseEntity existsByNameAndBuilding(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "building", required = true) String building) {
        try {
            Building found = buildingService.findByName(building);
            return ResponseEntity.status(HttpStatus.OK).body(classroomService.existsByNameAndBuilding(name, found));
        } catch (BuildingNotExistsException ex) {
            return handleException(ex);
        }
    }

    @GetMapping("/getNamesByBuilding")
    public ResponseEntity getNamesByBuilding(@RequestParam(value = "building", required = true) String building) {
        try {
            return ResponseEntity.ok(classroomService.getNamesByBuilding(building));
        } catch (BuildingNotExistsException ex) {
            return handleException(ex);
        }
    }
}
