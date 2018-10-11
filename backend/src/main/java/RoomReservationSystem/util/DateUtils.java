package RoomReservationSystem.util;

import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
public class DateUtils {

    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Megfelelő formátumú szöveg Timestamp objektummű konvertálása
     *
     * @param dateTimeString A dátum és idő szöveges reprezentációja (yyyy-MM-dd
     * HH:mm:ss)
     * @return A Timestamp objektum
     */
    public static Timestamp getTimestamp(String dateTimeString) {
        try {
            Date parsedDate = TIMESTAMP_FORMAT.parse(dateTimeString);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Megfelelő formátumú szöveg Date objektummá konvertálása
     *
     * @param dateString A dátum szöveges reprezentációja
     * @return A dátum, ha nem váltódik ki hiba
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
     * A függvény amely leellenőrzi, hogy a paraméterben kapott két dátum
     * (String) közül az első megelőzi-e a másodikat (korábban van-e?)
     *
     * @param startDateStr Az első dátum szöveges reprezentációja
     * @param endDateStr A második dátum szöveges reprezentációja
     * @return Igaz, ha az első dátum korábban van mint a második, hamis
     * egyébként
     */
    public static boolean isBefore(String startDateStr, String endDateStr) {
        Timestamp startDate = getTimestamp(startDateStr);
        Timestamp endDate = getTimestamp(endDateStr);
        return startDate.before(endDate);
    }

    /**
     * A függvény amely leellenőrzi, hogy a paraméterben kapott két dátum
     * (String) közül az első megelőzi-e a másodikat (korábban van-e?)
     *
     * @param startDateStr Az első dátum szöveges reprezentációja
     * @param endDateStr A második dátum szöveges reprezentációja
     * @return Igaz, ha az első dátum korábban van mint a második, hamis
     * egyébként
     */
    public static boolean isBeforeDate(String startDateStr, String endDateStr) {
        Date startDate = getDate(startDateStr);
        Date endDate = getDate(endDateStr);
        return startDate.before(endDate);
    }

    /**
     * A függvény amely leellenőrzi, hogy a paraméterben kapott két dátumokat
     * tartalmazó tömb elemei közül a kezdeti dátumok megelőzik-e a hozzájuk
     * tartozó befejezési dátumokat
     *
     * @param startDates A kezdeti dátumok
     * @param endDates A befejezési dátumok
     * @return Igaz, ha a kezdeti dátumok megelőzik a hozzájuk tartozó
     * befejezési dátumokat ,hamis egyébként
     */
    public static boolean areBefore(String[] startDates, String[] endDates) {
        if (startDates.length != endDates.length) {
            return false;
        } else {
            boolean l = true;
            int i = 0;
            while (l && i < startDates.length) {
                l &= isBefore(startDates[i], endDates[i]);
                i++;
            }
            return l;
        }
    }

    /**
     * A függvény amely visszaadja egy adott foglaláshoz tartozó kezdeti
     * dátumokat
     *
     * @param reservation A foglalás
     * @return A kezdeti dátumokat tartalmazó String tömb
     */
    public static String[] getStartDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        dates.forEach((date) -> {
            dateStrs.add(TIMESTAMP_FORMAT.format(date.getStart()));
        });
        return dateStrs.toArray(new String[0]);
    }

    /**
     * A függvény amely visszaadja egy adott foglaláshoz tartozó befejezési
     * dátumokat
     *
     * @param reservation A foglalás
     * @return A befejezési dátumokat tartalmazó String tömb
     */
    public static String[] getEndDates(ClassReservation reservation) {
        List<ReservationDate> dates = reservation.getDateList();
        List<String> dateStrs = new ArrayList<>();
        dates.forEach((date) -> {
            dateStrs.add(TIMESTAMP_FORMAT.format(date.getEnd()));
        });
        return dateStrs.toArray(new String[0]);
    }

    /**
     * A függvény amely előállítja egy adott foglaláshoz a megfelelő
     * ReservationDate objektumokat egy listában
     *
     * @param reservation A foglalás
     * @param startDates A kezdeti dátumok
     * @param endDates A befejezési dátumok
     * @return A megfelelő ReservationDate objektumok egy listában
     */
    public static List<ReservationDate> getReservationDates(ClassReservation reservation, String[] startDates, String[] endDates) {
        if (startDates.length != endDates.length) {
            return null;
        }

        List<ReservationDate> dates = new ArrayList<>();
        for (int i = 0; i < startDates.length; i++) {
            dates.add(new ReservationDate(
                    reservation,
                    getTimestamp(startDates[i]),
                    getTimestamp(endDates[i])
            ));
        }

        return dates;
    }
}
