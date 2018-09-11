package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.repository.SemesterRepository;
import RoomReservationSystem.service.SemesterService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A félévekkel kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;
    
    /**
     * A félévek lekérdezését megvalósító függvény
     * @return  A félévek egy listában
     */
    @Override
    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }
    
    /**
     * A félév mentését megvalósító függvény
     * @param   semester    A menteni kívánt félév
     * @return              A mentett félév
     * @throws RoomReservationSystem.exception.SemesterAlredyExistsException
     */
    @Override
    public Semester save(Semester semester) throws SemesterAlredyExistsException {
        Semester found = semesterRepository.findByName(semester.getName());
        if (found == null)
            return semesterRepository.save(semester);
        else
            throw new SemesterAlredyExistsException(String.format("Ilyen névvel (%s) rendelkező szemeszter már létezik!", semester.getName()));
        
    }
    
    /**
     * A szemeszterek neveinek lekérdezését megvalósító függvény
     * @return  A szemeszterek nevei egy listában
     */
    @Override
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        semesterRepository.findAll().forEach( (semester) -> {
            names.add(semester.getName());
        });
        return names;
    }
    
    /**
     * A félévek név szerint történő keresését megvalósító függvény
     * @param   name    A félév neve
     * @return          A félév ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.SemesterNotExistsException
     */
    @Override
    public Semester findByName(String name) throws SemesterNotExistsException{
        Semester found = semesterRepository.findByName(name);
        if (found != null)
            return found;
        else
            throw new SemesterNotExistsException(String.format("Ilyen névvel (%s) rendelkező szemeszter nem létezik!", name));
    }
    
    /**
     * A félévek név alapján történő törlését megvalósító függvény
     * @param   name    A félév neve
     * @throws RoomReservationSystem.exception.SemesterNotExistsException
     */
    @Override
    public void deleteByName(String name) throws SemesterNotExistsException{
        if (semesterRepository.findByName(name) != null)
            semesterRepository.deleteByName(name);
        else
            throw new SemesterNotExistsException(String.format("Ilyen névvel (%s) rendelkező szemeszter nem létezik!", name));
    }
    
    /**
     * A DTO objektum alapján történő keresést megvalósító függvény
     * (Annak ismeretében hogy melyik attribútum egyedi)
     * @param   semesterDTO    A DTO objektum
     * @return                 A Semester objektum ha létezik
     */
    @Override
    public Semester findByDTO(SemesterDTO semesterDTO) {
        return semesterRepository.findByName(semesterDTO.getName());
    }  
}
