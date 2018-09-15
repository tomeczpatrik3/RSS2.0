package RoomReservationSystem.exception;

/**
 * Az EventReservationAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class EventReservationAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public EventReservationAlredyExistsException(String message) {
        super(message);
    }
}
