package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.reservation.ReservationDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A foglalásokhoz tartozó végpontokat tartalmazó ősosztály
 * @author Tomecz Patrik
 */
public abstract class ReservationApiController {
     /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     * @return A megfelelő foglalások egy listában
     */
    public abstract ResponseEntity getAccepted();
    
    
    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     * @param username A felhasználónév
     * @return A megfelelő foglalások egy listában
     */
    public abstract ResponseEntity findByUsername(@PathVariable String username);
    
    /**
     * A függvény ami visszaadja az adott státusszal rendelkező foglalásokat
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity findByStatus(@PathVariable String status);
    
    /**
     * A függvény ami visszaadja az adott épület adott terméhez tartozó foglalásokat
     * @param building Az épület
     * @param classroom A terem
     * @return
     */
    public abstract ResponseEntity findByBuildingAndClassroom(@RequestParam(value="building", required=true) String building, @RequestParam(value="classroom", required=true) String classroom);

    /**
     * A függvény ami beállítja egy adott foglalást státuszát a paraméterben átadott értékre
     * @param id A foglalás azonosítója
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity setStatus(@RequestParam(value="id", required=true) int id, @RequestParam(value="status", required=true) String status);
    
//    /**
//     * A függvény ami létrehozza a megfelelő eseményre vonatkozó foglalást
//     * @param <T> Valamilyen ReservationDTO-ból származó típus
//     * @param reservationDTO A foglalás
//     * @param bindingResult
//     * @return A megfelelő válasz entitás
//     */
//    public abstract <T extends ReservationDTO> ResponseEntity createReservation(@RequestBody T reservationDTO, BindingResult bindingResult);
    
    public abstract ResponseEntity deleteByUsername(@RequestParam(value="username", required=true) String username);
    
    public abstract ResponseEntity deleteByBuildingAndClassroom(@RequestParam(value="building", required=true) String building, @RequestParam(value="classroom", required=true) String classroom);
    
    public abstract ResponseEntity deleteByStatus(@RequestParam(value="status", required=true) String status);             
}
