package RoomReservationSystem.service;

import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.model.Authority;

/**
 * Az engedélyekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface AuthorityService {

    Authority save(Authority authority) throws AuthorityAlredyExistsException;

    Authority findByName(String name) throws AuthorityNotExistsException;

    Authority findById(int id) throws AuthorityNotExistsException;

    void removeByName(String name) throws AuthorityNotExistsException;
}
