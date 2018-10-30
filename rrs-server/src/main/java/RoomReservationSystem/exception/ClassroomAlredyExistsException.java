package RoomReservationSystem.exception;

/**
 * A ClassroomAlredyExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassroomAlredyExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param building Az épület
     * @param room A tanterem
     */
    public ClassroomAlredyExistsException(String building, String room) {
        super(String.format("Ehhez az épülethez (%s) már tartozik ilyen nevű (%s) tanterem!", building, room));
    }
}
