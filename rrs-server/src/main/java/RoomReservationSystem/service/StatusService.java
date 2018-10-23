package RoomReservationSystem.service;

import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.Status;

/**
 * A státuszokkal kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface StatusService {

    Status findByName(String name) throws StatusNotExistsException;

    boolean existsById(int id);

    boolean existsByName(String name);

}
