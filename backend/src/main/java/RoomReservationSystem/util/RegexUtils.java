package RoomReservationSystem.util;

import java.util.regex.Pattern;

/**
 * A reguláris kifejezésekkel történő ellenőrzést megvalósító osztály
 * @author Tomecz Patrik
 */
public class RegexUtils {
    /*Dátumok ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern DATE_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
    /*Idő ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern TIME_REGEX = Pattern.compile("^[\\d]{2}:[\\d]{2}$");
    /*E-mail ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");
    /*Szemeszter ellenőrzésére szolgáló reguláris kifejezés*/
    private static final Pattern SEMESTER_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{4}-[\\d]{1}$");
    
    /**
     * Egy dátum String formátumát ellenőrző metódus
     * @param   dateString  A dátum Stringként
     * @return              Igaz, ha a dátum megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidDate(String dateString) {
        return DATE_REGEX.matcher(dateString).matches();
    }
    
    /**
     * Egy idő String formátumát ellenőrző metódus
     * @param   timeString  Az idő
     * @return              Igaz, ha az idő megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidTime(String timeString) {
        return TIME_REGEX.matcher(timeString).matches();
    }
    
    /**
     * Egy email String formátumát ellenőrző metódus
     * @param   emailString Az email cím 
     * @return              Igaz, ha az email cím megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidEmail(String emailString) {
        return EMAIL_REGEX.matcher(emailString).matches();
    }
    
    /**
     * Egy szemeszter String formátumát ellenőrző metódus
     * @param   semesterString  A szemeszter
     * @return                  Igaz, ha a szemeszter megfelel a formátumnak, hamis egyébként
     */
    public static boolean isValidSemester(String semesterString) {
        return SEMESTER_REGEX.matcher(semesterString).matches();
    }
}
