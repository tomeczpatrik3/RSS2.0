package RoomReservationSystem.exception;

/**
 * Az UserNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class UserNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public UserNotExistsException(String message) {
        super(message);
    }
}
