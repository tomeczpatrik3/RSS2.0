package RoomReservationSystem.exception;

/**
 * A BuildingNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class BuildingNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public BuildingNotExistsException(String message) {
        super(message);
    }
}
