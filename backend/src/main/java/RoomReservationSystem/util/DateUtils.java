package RoomReservationSystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DateUtils {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Megfelelő formátumú szöveg DateTime objektummű konvertálása
     * @param   dateTimeString  A dátum és idő szöveges reprezentációja (yyyy-MM-dd HH:mm:ss)
     * @return                  A DateTime objektum
     */
    public static DateTime getDateTime(String dateTimeString) {
        return dateTimeFormat.parseDateTime(dateTimeString);
    }
    
    /**
     * Megfelelő formátumú szöveg Date objektummá konvertálása
     * @param   dateString  A dátum szöveges reprezentációja
     * @return              A dátum, ha nem váltódik ki hiba
     */
    public static Date getDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
