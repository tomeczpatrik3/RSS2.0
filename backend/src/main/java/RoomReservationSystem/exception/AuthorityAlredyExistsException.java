package RoomReservationSystem.exception;

/**
 * Az AuthorityAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class AuthorityAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public AuthorityAlredyExistsException(String message) {
        super(message);
    }
}
