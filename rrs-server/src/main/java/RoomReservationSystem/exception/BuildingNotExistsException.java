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
     * @param name Az épület neve
     */
    public BuildingNotExistsException(String name) {
        super(String.format("Nem létezik ilyen névvel (%s) rendelkező épület!", name));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public BuildingNotExistsException(int id) {
        super(String.format("Nem létezik ilyen azonosítóval (%d) rendelkező épület!", id));
    }
}
