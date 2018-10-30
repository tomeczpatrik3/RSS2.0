package RoomReservationSystem.exception;

/**
 * A SubjectNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SubjectNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public SubjectNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező tantárgy nem létezik!", id));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param code A tárgykód
     */
    public SubjectNotExistsException(String code) {
        super(String.format("Ilyen kóddal (%s) rendelkező tantárgy nem létezik!", code));
    }
}
