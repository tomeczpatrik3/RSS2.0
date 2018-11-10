package RoomReservationSystem.service;

import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.SemesterNotOpenedException;
import RoomReservationSystem.model.Semester;
import java.util.Date;
import java.util.List;

/**
 * A félévekkel kapcsolatos műveletekért felelős interfész Részletes információ
 * a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface SemesterService {

    List<Semester> getAll();
    
    List<Semester> getOpened();

    List<String> getOpenedNames();

    Semester save(Semester semester) throws SemesterAlredyExistsException;

    Semester findByName(String name) throws SemesterNotExistsException;
    
    Semester findOpenedByName(String name) throws SemesterNotExistsException, SemesterNotOpenedException;
    
    Semester findById(int id) throws SemesterNotExistsException;
    
    Semester findOpenByDate(Date date) throws SemesterNotExistsException, SemesterNotOpenedException;
    
    Semester update(int id, Semester semester) throws SemesterNotExistsException;

    boolean existsById(int id);

    boolean existsByName(String name);

    void deleteByName(String name) throws SemesterNotExistsException;
}
