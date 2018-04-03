package RoomReservationSystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    
    /**
     * Egy adott naphoz tartozó DayOfWeek objektum lekérdezését megvalósító függvény
     * @param   day A nap szöveges formátumban
     * @return      A megfelelő DayOfWeek objektum
     */
    public static DayOfWeek getDayOfWeek(String day) {
        switch (day.toUpperCase()) {
            case "HÉTFŐ":
                return DayOfWeek.MONDAY;
            case "KEDD":
                return DayOfWeek.TUESDAY;
            case "SZERDA":
                return DayOfWeek.WEDNESDAY;
            case "CSÜTÖRTÖK":
                return DayOfWeek.THURSDAY;
            case "PÉNTEK":
                return DayOfWeek.FRIDAY;
            case "SZOMBAT":
                return DayOfWeek.SATURDAY;
            default:
                return DayOfWeek.SUNDAY;                
        }
    }
    
    /**
     * A DateTimeString előállításáért felelős függvény
     * @param date  A LocalDate objektum
     * @param time  Az idő String reprezentációban (óó:pp)
     * @return      A DateTime mintának megfelelő String objektum
     */
    public static String getDateTimeString(LocalDate date, String time) {
        String year = date.getYear()+"";
        String month = date.getMonthValue()<10 ? "0"+date.getMonthValue() : date.getMonthValue()+"";
        String day = date.getDayOfMonth()<10 ? "0"+date.getDayOfMonth() : date.getDayOfMonth()+"";
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];
        return String.format("%s-%s-%s %s:%s:00", year, month, day, hour, minute);
    }
    
    public static String getDateTimeString(String date, String time) {
        return String.format("%s %s:00", date, time);
    }
    
}

















