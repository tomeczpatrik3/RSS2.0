package RoomReservationSystem.exception;

/**
 * Az EventReservationNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class EventReservationNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public EventReservationNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező foglalás nem létezik", id));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param name Az esemény neve
     */
    public EventReservationNotExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező foglalás nem létezik", name));
    }
}
