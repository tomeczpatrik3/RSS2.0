package RoomReservationSystem.exception;

/**
 * A SubjectAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SubjectAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param code A tárgykód
     */
    public SubjectAlredyExistsException(String code) {
        super(String.format("Ilyen kóddal (%s) rendelkező tantárgy már létezik!", code));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public SubjectAlredyExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező tantárgy már létezik!", id));
    }
}
