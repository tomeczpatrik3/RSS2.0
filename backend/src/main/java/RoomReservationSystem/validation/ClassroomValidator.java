package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.ClassroomDTO;
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
      return clazz == ClassroomDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "name", "classroomDTO.name.empty", CLASSROOMDTO_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "hasPC", "classroomDTO.hasPC.empty", CLASSROOMDTO_HAS_PC_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "hasProjector", "classroomDTO.hasProjector.empty", CLASSROOMDTO_HAS_PROJECTOR_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "chairs", "classroomDTO.chairs.empty", CLASSROOMDTO_CHAIRS_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "building", "classroomDTO.building.empty", CLASSROOMDTO_BUILDING_EMPTY);

        ClassroomDTO classroom = (ClassroomDTO) target;

        if (this.classroomService.findByName( classroom.getName() ) != null) {
            errors.rejectValue("name", "classroomDTO.name.alredyExists", CLASSROOMDTO_NAME_ALREDY_EXISTS);
        }
        
        if (classroom.getName() != null && classroom.getName().length()<3 ||  classroom.getName().length()>30) {
            errors.rejectValue("name", "classroomDTO.name.size", CLASSROOMDTO_NAME_SIZE);
        }

        if (classroom.getChairs() < 0) {
            errors.rejectValue("chairs", "classroomDTO.chairs.value", CLASSROOMDTO_CHAIRS_VALUE);
        }
        
        if (classroom.getBuilding() != null && classroom.getBuilding().length()<3 ||  classroom.getBuilding().length()>30) {
            errors.rejectValue("building", "classroomDTO.building.size", CLASSROOMDTO_BUILDING_SIZE);
        }
    }
}