package RoomReservationSystem.exception;

/**
 * Az AuthorityAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class AuthorityAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param name Az engedély neve
     */
    public AuthorityAlredyExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező engedély már létezik!", name));
    }
}
