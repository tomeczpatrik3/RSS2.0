package RoomReservationSystem.exception;

/**
 * A ClassReservationAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassReservationAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public ClassReservationAlredyExistsException(String message) {
        super(message);
    }
}
