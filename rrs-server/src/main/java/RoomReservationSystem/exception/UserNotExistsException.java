package RoomReservationSystem.exception;

/**
 * Az UserNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class UserNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public UserNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező felhasználó nem létezik!", id));
    }
    
     /**
     * Az osztály konstruktora
     *
     * @param data Az "adat"
     */
    public UserNotExistsException(String data) {
        super(String.format("Ilyen adatokkal (%s) rendelkező felhasználó nem létezik!", data));
    }
}
