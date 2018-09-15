package RoomReservationSystem.exception;

/**
 * A SubjectAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SubjectAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public SubjectAlredyExistsException(String message) {
        super(message);
    }
}
