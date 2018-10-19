package RoomReservationSystem.service;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.model.Subject;

import java.util.List;

/**
 * A tantárgyakkal kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface SubjectService {

    Subject save(Subject subject) throws SubjectAlredyExistsException;

    Subject findByCode(String code) throws SubjectNotExistsException;

    Subject findById(int id) throws SubjectNotExistsException;

    List<Subject> findAll();

    List<Subject> findByName(String name);

    List<String> getSubjectNames();
    
    String getSubjectName(String subjectCode) throws SubjectNotExistsException;

    Subject findByDTO(SubjectDTO subjectDTO);

    boolean existsById(int id);

    boolean existsByCode(String code);

    void deleteByCode(String code) throws SubjectNotExistsException;
}
