package RoomReservationSystem.service;

import RoomReservationSystem.model.Semester;
import java.util.List;

/**
 * A félévekkel kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SemesterService {
    List<Semester> getAll();
    List<String> getNames();
    Semester save(Semester semester);
    Semester findByName(String name);
    void delete(Semester semester);
    void deleteByName(String name);
}
