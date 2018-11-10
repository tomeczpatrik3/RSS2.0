package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.repository.SubjectRepository;
import RoomReservationSystem.service.SubjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A tantárgyakkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * A tantárgy mentését megvalósító függvény
     *
     * @param subject A menteni kívánt tantárgy
     * @return A mentett tantárgy
     * @throws SubjectAlredyExistsException A lehetséges kivétel, ha a tantárgy
     * már létezik
     */
    @Override
    public Subject save(Subject subject) throws SubjectAlredyExistsException {
        if (subjectRepository.findByCode(subject.getCode()) == null) {
            return subjectRepository.save(subject);
        } else {
            throw new SubjectAlredyExistsException(subject.getCode());
        }
    }

    /**
     * A tantárgy azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param subject A tantárgy
     * @return A frissített tantárgy
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public Subject update(int id, Subject subject) throws SubjectNotExistsException {
        if (subjectRepository.findById(id) == null) {
            throw new SubjectNotExistsException(id);
        } else {
            subject.setId(id);
            return subjectRepository.save(subject);
        }
    }

    /**
     * A tantárgy kód alapján történő törlését megvalósító függvény
     *
     * @param code A törlendő tantárgy kódja
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public void deleteByCode(String code) throws SubjectNotExistsException {
        if (subjectRepository.findByCode(code) != null) {
            subjectRepository.deleteByCode(code);
        } else {
            throw new SubjectNotExistsException(code);
        }
    }

    /**
     * A tantárgyak lekérdezését megvalósító függvény
     *
     * @return A tantárgyak egy listában
     */
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /**
     * A tantárgy id alapján történő lekérdezését megvalósító függvény
     *
     * @param id A tantárgy id-ja
     * @return A tantárgy ha létezik, null egyébként
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public Subject findById(int id) throws SubjectNotExistsException {
        Subject found = subjectRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new SubjectNotExistsException(id);
        }
    }

    /**
     * A tantárgy név alapján történő lekérdezését megvalósító függvény
     *
     * @param name A tantárgy neve
     * @return A tantárgy ha létezik, null egyébként
     */
    @Override
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }

    /**
     * A tantárgy kód alapján történő lekérdezését megvalósító függvény
     *
     * @param code A tantárgy kódja
     * @return A tantárgy ha létezik, null egyébként
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public Subject findByCode(String code) throws SubjectNotExistsException {
        Subject found = subjectRepository.findByCode(code);
        if (found != null) {
            return found;
        } else {
            throw new SubjectNotExistsException(code);
        }
    }

    /**
     * A tantárgyak neveinek lekérdezését megvalósító függvény
     *
     * @return A tantárgyak nevei egy listában
     */
    @Override
    public List<String> getSubjectNames() {
        Iterable<Subject> subjects = this.findAll();
        List<String> subjectNames = new ArrayList<>();

        for (Subject sub : subjects) {
            subjectNames.add(sub.getName());
        }

        return subjectNames;
    }

    /**
     * A függvény ami visszaadja egy adott tárgykódhoz tartozó a tantárgy nevét
     *
     * @param subjectCode A tárgykód
     * @return A tantárgy neve
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public Map<String, String> getSubjectName(String subjectCode) throws SubjectNotExistsException {
        return Collections.singletonMap("name", findByCode(subjectCode).getName());
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
        return subjectRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott tárgykódhoz
     * tartozó entitás
     *
     * @param code A tárgykódhoz
     * @return Igen, ha létezik a tárgykódhoz tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByCode(String code) {
        return subjectRepository.existsByCode(code);
    }
}
