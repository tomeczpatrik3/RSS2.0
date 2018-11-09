package RoomReservationSystem.exception;

import java.util.Date;

/**
 * A SemesterNotExistsException osztály
 *
 * @author Tomecz Patrik
 */
public class SemesterNotExistsException extends Exception {

    /**
     * Az osztály konstruktora
     *
     * @param name A szemeszter neve
     */
    public SemesterNotExistsException(String name) {
        super(String.format("Ilyen névvel (%s) rendelkező szemeszter nem létezik!", name));
    }

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     */
    public SemesterNotExistsException(int id) {
        super(String.format("Ilyen azonosítóval (%d) rendelkező szemeszter nem létezik!", id));
    }

    /**
     * Az osztály konstruktora
     *
     * @param date A dátum
     */
    public SemesterNotExistsException(Date date) {
        super(String.format("Ilyen dátumhoz (%s) tartozó szemeszter nem létezik!", date.toString()));
    }
}
