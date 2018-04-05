package RoomReservationSystem.util;

import java.util.Arrays;
import java.util.List;

/**
 * A napokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DayUtils {
    /*A napokat tartalmazó lista*/
    private static final List DAYS = Arrays.asList("HÉTFŐ", "KEDD", "SZERDA", "CSÜTÖRTÖK", "PÉNTEK", "SZOMBAT", "VASÁRNAP");

    /**
     * A nap "formátumát" ellenőrző függvény
     * @param   day A nap
     * @return      Igaz, ha a hét valamelyik napja, hamis egyébként
     */
    public static boolean isValidDay(String day) {
        return DAYS.contains(day.toUpperCase());
    }    
}
