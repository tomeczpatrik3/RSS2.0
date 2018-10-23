package RoomReservationSystem.api.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A foglalásokhoz tartozó végpontokat tartalmazó ősosztály
 *
 * @author Tomecz Patrik
 */
public abstract class ReservationApiController {

    /**
     * A függvény ami visszaadja az elfogadott foglalásokat
     *
     * @return A megfelelő foglalások egy listában
     */
    public abstract ResponseEntity getAccepted();

    /**
     * A függvény ami visszaadja a várakozó foglalásokat
     *
     * @return A megfelelő foglalások egy listában
     */
    public abstract ResponseEntity getPending();

    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity findById(@RequestParam(value = "id", required = true) int id);

    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó foglalásokat
     *
     * @param username A felhasználónév
     * @return A megfelelő foglalások egy listában
     */
    public abstract ResponseEntity findByUsername(@PathVariable String username);

    /**
     * A függvény ami visszaadja az adott státusszal rendelkező foglalásokat
     *
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity findByStatus(@PathVariable String status);

    /**
     * A függvény ami visszaadja az adott épület adott terméhez tartozó
     * foglalásokat
     *
     * @param building Az épület
     * @param classroom A terem
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity findByBuildingAndClassroom(@RequestParam(value = "building", required = true) String building, @RequestParam(value = "classroom", required = true) String classroom);

    /**
     * A függvény ami beállítja egy adott foglalást státuszát a paraméterben
     * átadott értékre
     *
     * @param id A foglalás azonosítója
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity setStatus(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "status", required = true) String status);

    /**
     * A függvény ami törli az adott felhasználónévhez tartozó foglalásokat
     *
     * @param username A felhasználónév
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity deleteByUsername(@RequestParam(value = "username", required = true) String username);

    /**
     * A függvény ami törli egy adott épület adott tanterméhez tartozó
     * foglalásokat
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity deleteByBuildingAndClassroom(@RequestParam(value = "building", required = true) String building, @RequestParam(value = "classroom", required = true) String classroom);

    /**
     * A függvény ami törli az adott státuszhoz tartozó foglalásokat
     *
     * @param status A státusz
     * @return A megfelelő válasz entitás
     */
    public abstract ResponseEntity deleteByStatus(@RequestParam(value = "status", required = true) String status);
}
