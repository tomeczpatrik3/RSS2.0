package RoomReservationSystem.service;

import RoomReservationSystem.model.Authority;

/**
 * Az engedélyekkel kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface AuthorityService {
    void delete(Authority authority);
    Authority save(Authority authority);
    Authority findByName(String name);
    Authority findById(int id);
}
