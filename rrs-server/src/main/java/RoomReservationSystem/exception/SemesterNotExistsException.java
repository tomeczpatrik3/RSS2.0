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
     * @param name A szemeszter neve
     */
    public SemesterNotExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező szemeszter nem létezik!", name));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public SemesterNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező szemeszter nem létezik!", id));
    }
}
