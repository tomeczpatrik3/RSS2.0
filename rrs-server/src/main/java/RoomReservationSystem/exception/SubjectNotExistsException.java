package RoomReservationSystem.exception;

/**
 * A SubjectNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SubjectNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public SubjectNotExistsException(String message) {
        super(message);
    }
}
