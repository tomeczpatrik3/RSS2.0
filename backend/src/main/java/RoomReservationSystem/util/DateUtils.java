package RoomReservationSystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DateUtils {
    /**
     * Megfelelő formátumú szöveg dátummá konvertálása
     * @param   dateString  A dátum szöveges reprezentációja (yyyy-MM-dd)
     * @return              A Date objektum
     */
    public static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString); 
        } catch (ParseException ex) {
            return new Date();
        }
    }    
}
