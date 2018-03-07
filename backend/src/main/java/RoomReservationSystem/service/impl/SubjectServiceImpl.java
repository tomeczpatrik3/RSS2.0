package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import static RoomReservationSystem.model.Subject.toSubject;
import java.util.ArrayList;
import RoomReservationSystem.repository.SubjectRepository;
import RoomReservationSystem.service.SubjectService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Override
    public Subject save(SubjectDTO subjectDTO){       
        return subjectRepository.save(toSubject(subjectDTO));
    }
    
    @Override
    public void delete(Subject subject){
        subjectRepository.delete(subject);
    }
    
    @Override
    public void deleteByName(String name){
        subjectRepository.deleteByName(name);
    }
    
    @Override
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }  
    
    @Override
    public Subject findById(int id) {
        return this.subjectRepository.findById(id);
    }
    
    @Override
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
    
    @Override
    public Subject findByCode(String code) {
        return subjectRepository.findByCode(code);
    } 
   
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
