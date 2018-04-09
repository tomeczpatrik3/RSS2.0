package RoomReservationSystem.validation;

import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.dto.ClassroomDTO;

import static RoomReservationSystem.config.ErrorMessageConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az osztálytermek adatainak ellenőrzését végző osztály
 * @author Tomecz Patrik
 */
@Service
public class ClassroomValidator implements Validator {
    
    @Autowired
    ClassroomService classroomService;
    
    @Autowired
    BuildingService buildingService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == ClassroomDTO.class;
    }

    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "name", "classroom.name.empty", CLASSROOM_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "hasPC", "classroom.hasPC.empty", CLASSROOM_HAS_PC_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "hasProjector", "classroom.hasProjector.empty", CLASSROOM_HAS_PROJECTOR_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "chairs", "classroom.chairs.empty", CLASSROOM_CHAIRS_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "building", "classroom.building.empty", CLASSROOM_BUILDING_EMPTY);

        ClassroomDTO classroom = (ClassroomDTO) target;

        /*Terem létezésének ellenőrzése*/
        if (classroomService.findByNameAndBuildingName( classroom.getName(), classroom.getBuilding() ) != null) {
            errors.rejectValue("name", "classroom.name.alredyExists", CLASSROOM_ALREDY_EXISTS);
        }
        
        /*Terem nevének validálása*/
        if (classroom.getName() != null && classroom.getName().length()<3 ||  classroom.getName().length()>30) {
            errors.rejectValue("name", "classroom.name.size", CLASSROOM_NAME_SIZE);
        }

        /*Székek számának validálása*/
        if (classroom.getChairs() < 0) {
            errors.rejectValue("chairs", "classroom.chairs.value", CLASSROOM_CHAIRS_VALUE);
        }
        
        /*Épület meglétének ellenőrzése*/
        if (buildingService.findByName(classroom.getBuilding()) == null) {
            errors.rejectValue("building", "classroom.building.notExists", BUILDING_NOT_EXISTS);
        }
    }
}