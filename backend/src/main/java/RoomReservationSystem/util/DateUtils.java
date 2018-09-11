package RoomReservationSystem.util;

import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Megfelelő formátumú szöveg DateTime objektummű konvertálása
     * @param   dateTimeString  A dátum és idő szöveges reprezentációja (yyyy-MM-dd HH:mm:ss)
     * @return                  A DateTime objektum
     */
    public static DateTime getDateTime(String dateTimeString) {
        return DATE_TIME_FORMAT.parseDateTime(dateTimeString);
    }
    
        /**
     * Megfelelő formátumú szöveg Date objektummá konvertálása
     * @param   dateString  A dátum szöveges reprezentációja
     * @return              A dátum, ha nem váltódik ki hiba
     */
    public static Date getDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * A függvény amely leellenőrzi, hogy a paraméterben kapott két
     * dátum (String) közül az első megelőzi-e a másodikat
     * (korábban van-e?)
     * @param startDateStr   Az első dátum szöveges reprezentációja
     * @param endDateStr   A második dátum szöveges reprezentációja
     * @return              Igaz, ha az első dátum korábban van mint a második, hamis egyébként
     */
    public static boolean isBefore(String startDateStr, String endDateStr) {
        DateTime startDate =  DATE_TIME_FORMAT.parseDateTime(startDateStr);
        DateTime endDate = DATE_TIME_FORMAT.parseDateTime(endDateStr);
        return startDate.isBefore(endDate);        
    }
    
    public static boolean isBeforeDate(String startDateStr, String endDateStr) {
        Date startDate = getDate(startDateStr);
        Date endDate = getDate(endDateStr);
        return startDate.before(endDate);
    }
    
    public static boolean areBefore(String[] startDates, String[] endDates) {
        if (startDates.length != endDates.length)
            return false;
        else {
            boolean l = true;
            int i = 0;
            while (l && i<startDates.length) {
                l &= isBefore(startDates[i], endDates[i]);
            }
            return l;
        }
    }
    
    public static String[] getStartDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        dates.forEach((date) -> {
            dateStrs.add(date.getStart().toString());
        });
        return (String[]) dateStrs.toArray();
    }
    
    public static String[] getEndDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        dates.forEach((date) -> {
            dateStrs.add(date.getEnd().toString());
        });
        return (String[]) dateStrs.toArray();
    }
    
    public static List<ReservationDate> getReservationDates(ClassReservation reservation, String[] startDates, String[] endDates) {
        if (startDates.length != endDates.length)
            return null;
        
        List<ReservationDate> dates = new ArrayList<>();
        for (int i=0; i<startDates.length; i++) {
            dates.add(new ReservationDate(
                    reservation,
                    getDateTime(startDates[i]),
                    getDateTime(endDates[i])
            ));
        }
        
        return dates;
    }
}

















