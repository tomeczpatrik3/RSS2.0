package RoomReservationSystem.api;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.validation.BuildingValidator;
import RoomReservationSystem.service.BuildingService;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTOList;
import RoomReservationSystem.exception.BuildingAlredyExistsException;
import RoomReservationSystem.exception.BuildingNotExistsException;
import static RoomReservationSystem.model.Building.toBuilding;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Az épületekhez tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/building")
public class BuildingApiController {
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private BuildingValidator buildingValidator;

    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található
     * épületet
     *
     * @return Az épületek egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(toBuildingDTOList(buildingService.findAll()));
    }

    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található
     * épület nevét
     *
     * @return A nevek egy listában
     */
    @GetMapping("/getNames")
    public ResponseEntity getNames() {
        return ResponseEntity.ok(buildingService.getNames());
    }

    /**
     * A függvény ami visszaadja az adott névhez tartozó épületet
     *
     * @param name A név
     * @return A megfelelő épület
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(toBuildingDTO(buildingService.findByName(name)));
        } catch (BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott azonosítóhoz tartozó épületet
     *
     * @param id Az azonosító
     * @return A megfelelő épület
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(toBuildingDTO(buildingService.findById(id)));
        } catch (BuildingNotExistsException | NullPointerException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami létrehozza a megfelelő épületet
     *
     * @param buildingDTO Az épület
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createBuilding")
    public ResponseEntity createBuilding(@RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        buildingValidator.validate(buildingDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Building saved = buildingService.save(toBuilding(buildingDTO));
                return ResponseEntity.status(HttpStatus.CREATED).body(toBuildingDTO(saved));
            } catch (BuildingAlredyExistsException | NullPointerException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami törli az adott névhez tartozó épületet
     *
     * @param name A név
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteByName(@PathVariable String name) {
        try {
            buildingService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);
        } catch (BuildingNotExistsException | NullPointerException ex) {
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
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.existsById(id));
    }
    
    /**
     * A függvény ami visszaadja, hogy létezik-e az adott névhez tartozó
     * entitás
     *
     * @param name Az épület neve
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/existsByName")
    public ResponseEntity existsByName(@RequestParam(value = "name", required = true) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.existsByName(name));
    }
}
