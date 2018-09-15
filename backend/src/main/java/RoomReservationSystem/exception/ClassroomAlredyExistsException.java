package RoomReservationSystem.exception;

/**
 * A ClassroomAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassroomAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public ClassroomAlredyExistsException(String message) {
        super(message);
    }
}
