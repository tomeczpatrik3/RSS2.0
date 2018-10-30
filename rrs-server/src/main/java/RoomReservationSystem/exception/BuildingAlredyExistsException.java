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
     * @param name Az épület neve
     */
    public BuildingAlredyExistsException(String name) {
        super(String.format("Már létezik ilyen névvel (%s) rendelkező épület!", name));
    }
}
