package RoomReservationSystem.exception;

/**
 * Az EventReservationAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class EventReservationAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param name Az esemény neve
     */
    public EventReservationAlredyExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező esemény foglalás már létezik!", name));
    }
}
