package RoomReservationSystem.exception;

/**
 * A ClassReservationNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassReservationNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public ClassReservationNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező foglalás nem létezik", id));
    }
}
