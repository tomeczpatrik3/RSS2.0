package RoomReservationSystem.util;

import RoomReservationSystem.controller.api.BuildingApiController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A kivételekkel kapcsolatos segédfüggvényeket tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
public class ExceptionUtils {

    /**
     * A függvény amely elkészíti a megfelelő válasz entitást a kiváltott
     * kivétel segítségével
     *
     * @param <T> Az Exception osztályból leszármazó típus
     * @param ex Az kiváltódott kivéteel
     * @return A megfelelő válasz entitás
     */
    public static <T extends Exception> ResponseEntity handleException(T ex) {
        Logger.getLogger(BuildingApiController.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
