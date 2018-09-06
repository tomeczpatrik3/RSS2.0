package RoomReservationSystem.service;

import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.exception.InvalidParameterException;
import RoomReservationSystem.model.Authority;

/**
 * Az engedélyekkel kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface AuthorityService {
    void removeByName(String name) throws InvalidParameterException;
    Authority save(Authority authority) throws InvalidParameterException, AuthorityAlredyExistsException;
    Authority findByName(String name) throws InvalidParameterException, AuthorityNotExistsException;
    Authority findById(int id) throws AuthorityNotExistsException;
}
