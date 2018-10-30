package RoomReservationSystem.exception;

/**
 * A StatusNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class StatusNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param name A státusz neve
     */
    public StatusNotExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező státusz nem létezik!", name));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public StatusNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező státusz nem létezik!", id));
    }
}
