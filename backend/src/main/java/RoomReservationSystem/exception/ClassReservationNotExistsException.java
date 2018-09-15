package RoomReservationSystem.exception;

/**
 * A ClassReservationNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassReservationNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public ClassReservationNotExistsException(String message) {
        super(message);
    }
}
