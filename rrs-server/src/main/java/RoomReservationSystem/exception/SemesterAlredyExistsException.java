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
     * @param name A szemeszter neve
     */
    public SemesterAlredyExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező szemeszter már létezik!", name));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public SemesterAlredyExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező szemeszter nem létezik!", id));
    }
}
