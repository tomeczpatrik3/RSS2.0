package RoomReservationSystem.exception;

/**
 * A MessageNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class MessageNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public MessageNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező üzenet nem létezik!", id));
    }
}
