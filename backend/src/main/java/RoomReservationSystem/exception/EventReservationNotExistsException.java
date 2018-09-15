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
     * @param message A hibaüzenet
     */
    public EventReservationNotExistsException(String message) {
        super(message);
    }
}
