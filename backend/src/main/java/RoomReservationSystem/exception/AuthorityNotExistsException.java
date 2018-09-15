package RoomReservationSystem.exception;

/**
 * Az AuthorityNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class AuthorityNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public AuthorityNotExistsException(String message) {
        super(message);
    }
}
