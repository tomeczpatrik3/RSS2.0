package RoomReservationSystem.service;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;

import java.util.List;

/**
 * A tantárgyakkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface SubjectService {
    void delete(Subject subject);
    void deleteByCode(String code);
    Subject save(Subject subject);
    Subject findByCode(String code);
    Subject findById(int id);
    List<Subject> findAll();
    List<Subject> findByName(String name);
    List<String> getSubjectNames();
}
