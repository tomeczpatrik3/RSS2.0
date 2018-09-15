package RoomReservationSystem.exception;

/**
 * A BuildingAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class BuildingAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param message A hibaüzenet
     */
    public BuildingAlredyExistsException(String message) {
        super(message);
    }
}
