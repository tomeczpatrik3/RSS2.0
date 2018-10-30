package RoomReservationSystem.exception;

/**
 * Az UserAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class UserAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param data Az "adat"
     */
    public UserAlredyExistsException(String data) {
        super(String.format("Ilyen adatokkal (%s) rendelkező felhasználó már létezik!", data));
    }
}
