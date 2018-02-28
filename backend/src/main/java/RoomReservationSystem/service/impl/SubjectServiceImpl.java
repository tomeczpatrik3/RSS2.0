package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Subject;
import java.util.ArrayList;
import RoomReservationSystem.repository.SubjectRepository;
import RoomReservationSystem.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Override
    public Subject save(Subject subject){       
        return subjectRepository.save(subject);
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
    public Iterable<Subject> findAll(){
        return subjectRepository.findAll();
    }  
    
    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
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
