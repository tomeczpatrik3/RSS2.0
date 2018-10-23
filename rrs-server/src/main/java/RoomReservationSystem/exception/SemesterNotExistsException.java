package RoomReservationSystem.exception;

/**
 * A SemesterNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SemesterNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public SemesterNotExistsException(String message) {
        super(message);
    }
}
