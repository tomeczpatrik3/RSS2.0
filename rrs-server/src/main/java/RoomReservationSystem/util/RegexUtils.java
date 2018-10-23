package RoomReservationSystem.util;

import java.util.regex.Pattern;

/**
 * A reguláris kifejezésekkel történő ellenőrzést megvalósító segéd osztály
 *
 * @author Tomecz Patrik
 */
public class RegexUtils {

    /*Dátumok ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern DATE_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
    /*Dátumok ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern DATE_TIME_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}$");
    /*E-mail ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");
    /*Szemeszter ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern SEMESTER_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{4}/[\\d]{1}$");

    /**
     * Egy dátum String formátumát ellenőrző metódus
     *
     * @param dateString A dátum Stringként
     * @return Igaz, ha a dátum megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidDate(String dateString) {
        return DATE_REGEX.matcher(dateString).matches();
    }

    /**
     * Több dátum String formátumát ellenőrző metódus
     *
     * @param dates A dátumokat tartalmazó tömb
     * @return Igaz, ha a dátumok megfelelnek a formátumnak, hamis egyébként
     */
    public static boolean areValidDates(String[] dates) {
        boolean l = true;
        int i = 0;
        while (l && i < dates.length) {
            l &= isValidDate(dates[i]);
        }
        return l;
    }
    
    /**
     * Egy dátum-idő String formátumát ellenőrző metódus
     *
     * @param dateTimeString A dátum-idő Stringként
     * @return Igaz, ha a dátum-idő megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidDateTime(String dateTimeString) {
        return DATE_TIME_REGEX.matcher(dateTimeString).matches();
    }

    /**
     * Több dátum-idő String formátumát ellenőrző metódus
     *
     * @param dateTimes A dátum-időket tartalmazó tömb
     * @return Igaz, ha a dátum-idők megfelelnek a formátumnak, hamis egyébként
     */
    public static boolean areValidDateTimes(String[] dateTimes) {
        boolean l = true;
        int i = 0;
        while (l && i < dateTimes.length) {
            l &= isValidDateTime(dateTimes[i]);
            i++;
        }
        return l;
    }

    /**
     * Egy email String formátumát ellenőrző metódus
     *
     * @param emailString Az email cím
     * @return Igaz, ha az email cím megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidEmail(String emailString) {
        return EMAIL_REGEX.matcher(emailString).matches();
    }

    /**
     * Egy szemeszter String formátumát ellenőrző metódus
     *
     * @param semesterString A szemeszter
     * @return Igaz, ha a szemeszter megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidSemester(String semesterString) {
        return SEMESTER_REGEX.matcher(semesterString).matches();
    }
}
