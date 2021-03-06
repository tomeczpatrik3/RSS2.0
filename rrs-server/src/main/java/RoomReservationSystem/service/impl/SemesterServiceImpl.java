package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.SemesterNotOpenedException;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.repository.SemesterRepository;
import RoomReservationSystem.service.SemesterService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A félévekkel kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    /**
     * A félévek lekérdezését megvalósító függvény
     *
     * @return A félévek egy listában
     */
    @Override
    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    /**
     * A nyitott félévek lekérdezését megvalósító függvény
     *
     * @return A félévek egy listában
     */
    @Override
    public List<Semester> getOpened() {
        return semesterRepository.findAll().stream().filter(semester -> semester.isOpened() == true).collect(Collectors.toList());
    }

    /**
     * A félév mentését megvalósító függvény
     *
     * @param semester A menteni kívánt félév
     * @return A mentett félév
     * @throws SemesterAlredyExistsException A lehetséges kivétel, ha a
     * szemeszter már létezik
     */
    @Override
    public Semester save(Semester semester) throws SemesterAlredyExistsException {
        Semester found = semesterRepository.findByName(semester.getName());
        if (found == null) {
            return semesterRepository.save(semester);
        } else {
            throw new SemesterAlredyExistsException(semester.getName());
        }

    }

    /**
     * A szemeszeterek azonosító alapján történő frissítését megvalósító
     * függvény
     *
     * @param id Az azonosító
     * @param semester A szemeszter
     * @return A frissített szemeszter
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public Semester update(int id, Semester semester) throws SemesterNotExistsException {
        if (semesterRepository.findById(id) == null) {
            throw new SemesterNotExistsException(id);
        } else {
            semester.setId(id);
            return semesterRepository.save(semester);
        }
    }

    /**
     * A nyitott szemeszterek neveinek lekérdezését megvalósító függvény
     *
     * @return A megfelelő nevek egy listában
     */
    @Override
    public List<String> getOpenedNames() {
        List<Semester> openedSemesters = semesterRepository.findAll().stream().filter(semester -> semester.isOpened() == true).collect(Collectors.toList());
        return openedSemesters.stream().map(opened -> opened.getName()).collect(Collectors.toList());
    }

    /**
     * A félévek név szerint történő keresését megvalósító függvény
     *
     * @param name A félév neve
     * @return A félév ha létezik, null egyébként
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public Semester findByName(String name) throws SemesterNotExistsException {
        Semester found = semesterRepository.findByName(name);
        if (found != null) {
            return found;
        } else {
            throw new SemesterNotExistsException(name);
        }
    }

    /**
     * A nyílt félévek név szerint történő keresését megvalósító függvény
     *
     * @param name A félév neve
     * @return A félév ha létezik, null egyébként
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     */
    @Override
    public Semester findOpenedByName(String name) throws SemesterNotExistsException, SemesterNotOpenedException {
        Semester found = this.findByName(name);
        if (found.isOpened()) {
            return found;
        } else {
            throw new SemesterNotOpenedException(name);
        }
    }

    /**
     * A félévek azonosító szerint történő keresését megvalósító függvény
     *
     * @param id Az azonosító
     * @return A megfelelő félév
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public Semester findById(int id) throws SemesterNotExistsException {
        Semester found = semesterRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new SemesterNotExistsException(id);
        }
    }

    /**
     * Egy adott dátumhoz tartozó nyitott szemeszter keresését megvalósító
     * függvény
     *
     * @param date A dátum
     * @return A megfelelő szemeszter
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     */
    @Override
    public Semester findOpenByDate(Date date) throws SemesterNotExistsException, SemesterNotOpenedException {
        List<Semester> found = this.getAll().stream().filter(semester
                -> date.after(semester.getStartDate()) && date.before(semester.getEndDate()) && semester.isOpened()
        ).collect(Collectors.toList());
        if (found.size() == 1) {
            if (found.get(0).isOpened()) {
                return found.get(0);
            } else {
                throw new SemesterNotOpenedException(found.get(0).getName());
            }
        } else {
            throw new SemesterNotExistsException(date);
        }
    }

    /**
     * A félévek név alapján történő törlését megvalósító függvény
     *
     * @param name A félév neve
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public void deleteByName(String name) throws SemesterNotExistsException {
        if (semesterRepository.findByName(name) != null) {
            semesterRepository.deleteByName(name);
        } else {
            throw new SemesterNotExistsException(name);
        }
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott azonosítóhoz
     * tartozó entitás
     *
     * @param id Az azonosító
     * @return Igen, ha létezik az azonosítóhoz tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsById(int id) {
        return semesterRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott névhez tartozó
     * entitás
     *
     * @param name A név
     * @return Igen, ha létezik a névhez tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByName(String name) {
        return semesterRepository.existsByName(name);
    }
}
