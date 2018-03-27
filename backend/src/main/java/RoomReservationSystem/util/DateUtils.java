package RoomReservationSystem.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Megfelelő formátumú szöveg dátummá konvertálása
     * @param   dateString  A dátum és idő szöveges reprezentációja (yyyy-MM-dd HH:mm:ss)
     * @return              A DateTime objektum
     */
    public static DateTime getDate(String dateString) {
        return formatter.parseDateTime(dateString);
    }
}
