package RoomReservationSystem.exception;

/**
 * A StatusNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class StatusNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public StatusNotExistsException(String message) {
        super(message);
    }
}
