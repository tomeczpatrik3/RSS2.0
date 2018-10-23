package RoomReservationSystem.exception;

/**
 * A ClassroomNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassroomNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public ClassroomNotExistsException(String message) {
        super(message);
    }
}
