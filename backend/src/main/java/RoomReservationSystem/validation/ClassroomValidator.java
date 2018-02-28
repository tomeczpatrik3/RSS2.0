package RoomReservationSystem.validation;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.service.impl.ClassroomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ClassroomValidator implements Validator {
    
    @Autowired
    ClassroomServiceImpl classroomService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == Classroom.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "building", "classroom.building.empty");
        ValidationUtils.rejectIfEmpty(errors, "roomName", "classroom.roomName.empty");

        Classroom classroom = (Classroom) target;

        if (this.classroomService.findByName( classroom.getName() ) != null) {
            errors.rejectValue("roomName", "classroom.roomName.alredyExists");
        }
        
        if (classroom.getName() != null && classroom.getName().length()<3 ||  classroom.getName().length()>30) {
            errors.rejectValue("roomName", "classroom.roomName.size");
        }

        if (classroom.getChairs() < 0) {
            errors.rejectValue("chairs", "classroom.chairs.value");
        }
    }
}