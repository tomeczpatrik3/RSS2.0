package RoomReservationSystem.service;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.model.Semester;
import java.util.List;

/**
 * A félévekkel kapcsolatos műveletekért felelős interfész Részletes információ
 * a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface SemesterService {

    List<Semester> getAll();

    List<String> getNames();

    Semester save(Semester semester) throws SemesterAlredyExistsException;

    Semester findByName(String name) throws SemesterNotExistsException;
    
    Semester findById(int id) throws SemesterNotExistsException;
    
    Semester update(int id, Semester semester) throws SemesterNotExistsException;

    boolean existsById(int id);

    boolean existsByName(String name);

    void deleteByName(String name) throws SemesterNotExistsException;
}
