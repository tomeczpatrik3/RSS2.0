package RoomReservationSystem.exception;

/**
 * Az AuthorityNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class AuthorityNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public AuthorityNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező engedély nem létezik!", id));
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param name Az engedély neve
     */
    public AuthorityNotExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező engedély nem létezik!", name));
    }
}
