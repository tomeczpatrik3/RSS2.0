package RoomReservationSystem.exception;

/**
 * A SemesterNotOpenedException osztály
 *
 * @author Tomecz Patrik
 */
public class SemesterNotOpenedException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param name A szemeszter neve
     */
    public SemesterNotOpenedException(String name) {
        super(String.format("Az ezzel a névvel (%s) rendelkező szemeszter nincs megnyitva!", name));
    }
}
