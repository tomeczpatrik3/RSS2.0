package RoomReservationSystem.exception;

/**
 * Az UserAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class UserAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public UserAlredyExistsException(String message) {
        super(message);
    }
}
