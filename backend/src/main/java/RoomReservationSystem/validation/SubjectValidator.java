package RoomReservationSystem.validation;

import RoomReservationSystem.model.Subject;
import RoomReservationSystem.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class SubjectValidator implements Validator {
    
    @Autowired
    private SubjectServiceImpl subjectService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == Subject.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "subject.name.empty");

        Subject subject = (Subject)target;     
        
        if (subjectService.findByName(subject.getName()) != null) {
            errors.rejectValue("name", "subject.name.alredyExists");
        }
        
        if (subject.getName() != null && subject.getName().length() < 5 ||
                subject.getName().length() > 30) {
            errors.rejectValue("name", "subject.name.size");
        }
    }
}
