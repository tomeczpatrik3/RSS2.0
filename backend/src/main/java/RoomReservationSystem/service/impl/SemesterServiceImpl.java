package RoomReservationSystem.service.impl;

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
     */
    @Override
    public Semester save(Semester semester) {
        return semesterRepository.save(semester);
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
     */
    @Override
    public Semester findByName(String name){
        return semesterRepository.findByName(name);
    }
    
    /**
     * A félévek törlését megvalósító függvény
     * @param   semester    A törölni kívánt félév
     */
    @Override
    public void delete(Semester semester) {
        semesterRepository.delete(semester);
    }
    
    /**
     * A félévek név alapján történő törlését megvalósító függvény
     * @param   name    A félév neve
     */
    @Override
    public void deleteByName(String name) {
        semesterRepository.deleteByName(name);
    }
}
