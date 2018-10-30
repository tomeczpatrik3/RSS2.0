package RoomReservationSystem.exception;

/**
 * A ClassroomNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class ClassroomNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param building Az épület
     * @param room A tanterem
     */
    public ClassroomNotExistsException(String building, String room) {
        super(String.format("Ehhez az épülethez (%s) nem tartozik ilyen nevű (%s) tanterem!", building, room));
    }

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public ClassroomNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező tanterem nem létezik!", id));
    }
}
