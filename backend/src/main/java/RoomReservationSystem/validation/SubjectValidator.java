package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.SubjectDTO;
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
      return clazz == SubjectDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "subject.name.empty", SUBJECT_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "code", "subject.code.empty", SUBJECT_CODE_EMPTY);

        SubjectDTO subject = (SubjectDTO)target;     
        
        if (subjectService.findByCode(subject.getCode()) != null) {
            errors.rejectValue("code", "subject.code.alredyExists", SUBJECT_ALREDY_EXISTS);
        }
        
        if (subject.getName() != null && subject.getName().length() < 5 ||
                subject.getName().length() > 30) {
            errors.rejectValue("name", "subject.name.size", SUBJECT_NAME_SIZE);
        }
        
       if (subject.getCode() != null && subject.getCode().length() < 4 ||
                subject.getCode().length() > 10) {
            errors.rejectValue("code", "subject.code.size", SUBJECT_CODE_SIZE);
        }
    }
}
