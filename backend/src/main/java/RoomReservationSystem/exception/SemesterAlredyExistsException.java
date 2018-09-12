package RoomReservationSystem.exception;

/**
 * A SemesterAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SemesterAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public SemesterAlredyExistsException(String message) {
        super(message);
    }
}
