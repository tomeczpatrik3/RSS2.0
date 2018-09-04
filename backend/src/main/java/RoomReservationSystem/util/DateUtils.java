package RoomReservationSystem.util;

import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        }
        catch (ParseException ex) {
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
     * LocalDate és time (String) objektumokból DateTimeString előállításáért felelős függvény
     * @param date  A LocalDate objektum
     * @param time  Az idő String reprezentációban (óó:pp)
     * @return      A DateTime mintának megfelelő String objektum
     */
    public static String getDateTimeString(LocalDate date, String time) {
        String year = formatNumber(date.getYear());
        String month = formatNumber(date.getMonthValue());
        String day = formatNumber(date.getDayOfMonth());
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];
        return String.format("%s-%s-%s %s:%s:00", year, month, day, hour, minute);
    }
    
    /**
     * DateTime objektumból DateTimeString előállításáért felelős objektum
     * @param   dateTime    A DateTime objektum
     * @return              A DateTime mintának megfelelő String objektum
     */
    public static String getDateTimeString(DateTime dateTime) {
        String year = formatNumber(dateTime.year().get());
        String month = formatNumber(dateTime.monthOfYear().get());
        String day = formatNumber(dateTime.dayOfMonth().get());
        String hour = formatNumber(dateTime.getHourOfDay());
        String minute = formatNumber(dateTime.getMinuteOfHour());
        String second = formatNumber(dateTime.getSecondOfMinute());
        
        return String.format("%s-%s-%s %s:%s:%s", year, month, day, hour, minute, second);
    }
    
    /**
     * Date (String) és time (String) objektumokból DateTimeString előállításáért felelős függvény
     * @param   date    A dátum szöveges reprezentációja
     * @param   time    Az idő szöveges reprezentációja
     * @return          A DateTime mintának megfelelő String objektum
     */
    public static String getDateTimeString(String date, String time) {
        return String.format("%s %s:00", date, time);
    }
    
    private static String formatNumber(int number) {
        if (number<10)
            return String.format("0%d", number);
        else
            return String.format("%d", number);
    }
    
    /**
     * A függvény amely leellenőrzi, hogy a paraméterben kapott két
     * dátum (String) közül az első megelőzi-e a másodikat
     * (korábban van-e?)
     * @param dateStringA   Az első dátum szöveges reprezentációja
     * @param dateStringB   A második dátum szöveges reprezentációja
     * @return              Igaz, ha az első dátum korábban van mint a második, hamis egyébként
     */
    public static boolean isBefore(String dateStringA, String dateStringB) {
        try {
            Date startDate =  DATE_FORMAT.parse(dateStringA);
            Date endDate = DATE_FORMAT.parse(dateStringB);
            
            return startDate.before(endDate);
        } catch (ParseException ex) {
            return false;
        }        
    }
    
    public static String[] getStartDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        for (ReservationDate date: dates) {
            dateStrs.add(date.getStart().toString());
        }
        return (String[]) dateStrs.toArray();
    }
    
    public static String[] getEndDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        for (ReservationDate date: dates) {
            dateStrs.add(date.getEnd().toString());
        }
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

















