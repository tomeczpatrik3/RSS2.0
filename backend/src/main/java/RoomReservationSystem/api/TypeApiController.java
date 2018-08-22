package RoomReservationSystem.api;

import static RoomReservationSystem.config.ErrorMessageConstants.TYPE_NOT_EXISTS;
import RoomReservationSystem.dto.TypeDTO;
import static RoomReservationSystem.dto.TypeDTO.toTypeDTO;
import static RoomReservationSystem.dto.TypeDTO.toTypeDTOList;
import RoomReservationSystem.model.Type;
import static RoomReservationSystem.model.Type.toType;
import RoomReservationSystem.service.TypeService;
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

/**
 * A típusokhoz tartozó végpontokat tartalmazó osztály
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value="/api/type")
public class TypeApiController {
    
    @Autowired
    private TypeService typeService;
    
    /**
     * A függvény ami visszaadja egy listában az összes adatbázisban található típust
     * @return A típusok egy listában
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<TypeDTO> getAll(){
        return toTypeDTOList(typeService.findAll());
    }
    
    /**
     * A függvény ami létrehozza a megfelelő típust
     * @param typeDTO A típus
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/createType")
    public ResponseEntity createType(@RequestBody TypeDTO typeDTO, BindingResult bindingResult) {
        //typevalidator.validate(typeDTO, bindingResult);
        //if (!bindingResult.hasErrors()) {
            Type saved = typeService.save(toType(typeDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(toTypeDTO(saved));           
        //}
        //else {
        //    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        //}          
    }
    
    /**
     * A függvény ami törli az adott névhez tartozó típust
     * @param name A név
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByName")
    public ResponseEntity deleteByName(@RequestParam String name) {
        if ( typeService.findByName( name ) != null ) {
            typeService.deleteByName(name);
            return new ResponseEntity(HttpStatus.OK);          
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(TYPE_NOT_EXISTS);
        }
    }
}