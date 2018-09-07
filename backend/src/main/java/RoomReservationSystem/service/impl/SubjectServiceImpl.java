package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.repository.SubjectRepository;
import RoomReservationSystem.service.SubjectService;
import static RoomReservationSystem.model.Subject.toSubject;

import java.util.ArrayList;
import java.util.List;

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
     * @throws RoomReservationSystem.exception.SubjectAlredyExistsException
     */
    @Override
    public Subject save(Subject subject) throws SubjectAlredyExistsException {
        if (subjectRepository.findByCode(subject.getCode()) == null) {
            return subjectRepository.save(subject);
        } else {
            throw new SubjectAlredyExistsException(String.format("Ilyen kóddal (%s) rendelkező tantárgy már létezik!", subject.getCode()));
        }
    }

    /**
     * A tantárgy kód alapján történő törlését megvalósító függvény
     *
     * @param code A törlendő tantárgy kódja
     * @throws RoomReservationSystem.exception.SubjectNotExistsException
     */
    @Override
    public void deleteByCode(String code) throws SubjectNotExistsException {
        if (subjectRepository.findByCode(code) != null) {
            subjectRepository.deleteByCode(code);
        } else {
            throw new SubjectNotExistsException(String.format("Ilyen kóddal (%s) rendelkező tantárgy nem létezik!", code));
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
     * @throws RoomReservationSystem.exception.SubjectNotExistsException
     */
    @Override
    public Subject findById(int id) throws SubjectNotExistsException {
        Subject found = subjectRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new SubjectNotExistsException(String.format("Ilyen azonosítóval (%d) rendelkező tantárgy nem létezik!", id));
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
     * @throws RoomReservationSystem.exception.SubjectNotExistsException
     */
    @Override
    public Subject findByCode(String code) throws SubjectNotExistsException {
        Subject found = subjectRepository.findByCode(code);
        if (found != null) {
            return found;
        } else {
            throw new SubjectNotExistsException(String.format("Ilyen kóddal (%s) rendelkező tantárgy nem létezik!", code));
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
     * A DTO objektum alapján történő keresést megvalósító függvény (Annak
     * ismeretében hogy melyik attribútum egyedi)
     *
     * @param subjectDTO A DTO objektum
     * @return A Subject objektum ha létezik
     */
    @Override
    public Subject findByDTO(SubjectDTO subjectDTO) {
        return subjectRepository.findByCode(subjectDTO.getCode());
    }
}
