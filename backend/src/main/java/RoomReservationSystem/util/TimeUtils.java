package RoomReservationSystem.util;

/**
 * Az időkkel kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class TimeUtils {
    
    /**
     * Két idő String esetén megvizsgálja, hogy az első paraméter előbb van-e
     * @param timeStringA   Az első idő
     * @param timeStringB   A második idő
     * @return              Igaz, ha az első idő korábban van mint a második, hamis egyébként
     */
    public static boolean isBefore(String timeStringA, String timeStringB) {
        try {
            int hourA = Integer.parseInt(timeStringA.split(":")[0]);
            int minuteA = Integer.parseInt(timeStringA.split(":")[1]);

            int hourB = Integer.parseInt(timeStringB.split(":")[0]);
            int minuteB = Integer.parseInt(timeStringB.split(":")[1]);
            
            return ( hourA < hourB || (hourA == hourB && minuteA < minuteB) );
        } catch(Exception ex) {
            return false;
        }
    }
}
