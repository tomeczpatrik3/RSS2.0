package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SubjectDTO;
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
 * @author Tomecz Patrik
 */
@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    
    /**
     * A tantárgy mentését megvalósító függvény
     * @param   subject     A menteni kívánt tantárgy
     * @return              A mentett tantárgy
     */
    @Override
    public Subject save(Subject subject){       
        return subjectRepository.save(subject);
    }
    
    /**
     * A tantárgy törlését megvalósító függvény
     * @param   subject A törlendő tantárgy 
     */
    @Override
    public void delete(Subject subject){
        subjectRepository.delete(subject);
    }
    
    /**
     * A tantárgy kód alapján történő törlését megvalósító függvény
     * @param   code    A törlendő tantárgy kódja 
     */
    @Override
    public void deleteByCode(String code){
        subjectRepository.deleteByCode(code);
    }
    
    /**
     * A tantárgyak lekérdezését megvalósító függvény
     * @return  A tantárgyak egy listában
     */
    @Override
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }  
    
    /**
     * A tantárgy id alapján történő lekérdezését megvalósító függvény
     * @param   id  A tantárgy id-ja
     * @return      A tantárgy ha létezik, null egyébként
     */
    @Override
    public Subject findById(int id) {
        return this.subjectRepository.findById(id);
    }
    
    /**
     * A tantárgy név alapján történő lekérdezését megvalósító függvény
     * @param   name    A tantárgy neve
     * @return          A tantárgy ha létezik, null egyébként
     */
    @Override
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
    
    /**
     * A tantárgy kód alapján történő lekérdezését megvalósító függvény
     * @param   code    A tantárgy kódja
     * @return          A tantárgy ha létezik, null egyébként
     */
    @Override
    public Subject findByCode(String code) {
        return subjectRepository.findByCode(code);
    } 
   
    /**
     * A tantárgyak neveinek lekérdezését megvalósító függvény
     * @return  A tantárgyak nevei egy listában
     */
    @Override
    public List<String> getSubjectNames() {
        Iterable<Subject> subjects = this.findAll();
        List<String> subjectNames = new ArrayList<>();
        
        for (Subject sub: subjects) {
            subjectNames.add(sub.getName());
        }
        
        return subjectNames;
    }
    
}
