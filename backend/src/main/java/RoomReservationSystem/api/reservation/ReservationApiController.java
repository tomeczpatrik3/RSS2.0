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
     * @param <T> Valamilyen ReservationDTO-ból származó típus
     * @return A megfelelő foglalások egy listában
     */
    public abstract <T extends ReservationDTO> List<T> getAccepted();
    
    
    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     * @param <T> Valamilyen ReservationDTO-ból származó típus
     * @param username A felhasználónév
     * @return A megfelelő foglalások egy listában
     */
    public abstract <T extends ReservationDTO> List<T> findByUsername(@PathVariable String username);
    
    /**
     * A függvény ami visszaadja az adott státusszal rendelkező foglalásokat
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity findByStatus(@PathVariable String status);
    
    /**
     * A függvény ami visszaadja az adott terem kiválasztott időpontjára vonatkozó foglalásokat
     * @param <T> Valamilyen ReservationDTO-ból származó típus
     * @param building Az épület
     * @param classroom A terem
     * @param date  A dátum
     * @return A megfelelő foglalások egy listában
     */
    public abstract <T extends ReservationDTO> List<T> findByClassroomAndDate(
            @RequestParam("building") String building,
            @RequestParam("classroom") String classroom,
            @RequestParam("date") String date
    );
    
    /**
     * A függvény ami beállítja egy adott foglalást státuszát a paraméterben átadott értékre
     * @param id A foglalás azonosítója
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity setStatus(@RequestParam("id") int id, @RequestParam("status") String status);
    
    /**
     * A függvény ami létrehozza a megfelelő eseményre vonatkozó foglalást
     * @param <T> Valamilyen ReservationDTO-ból származó típus
     * @param reservationDTO A foglalás
     * @param bindingResult
     * @return A megfelelő válasz entitás
     */
    //public abstract <T extends ReservationDTO> ResponseEntity createReservation(@RequestBody T reservationDTO, BindingResult bindingResult);
}
