package RoomReservationSystem.service;

import RoomReservationSystem.model.Type;
import java.util.List;

/**
 * A foglalás típusokkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface TypeService {
    void delete(Type type);
    void deleteByName(String name);
    Type save(Type type);
    Type findByName(String name);
    List<Type> findAll();
}