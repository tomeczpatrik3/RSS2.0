package RoomReservationSystem.util;

import RoomReservationSystem.api.BuildingApiController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtils {
    public static <T extends Exception> ResponseEntity handleException(T ex) {
        Logger.getLogger(BuildingApiController.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }    
}
