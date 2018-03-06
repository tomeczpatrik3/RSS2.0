package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class BuildingValidator implements Validator {
    
    @Autowired
    BuildingServiceImpl buildingService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == BuildingDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "name", "buildingDTO.name.empty", BUILDING_NAME_EMPTY);

        BuildingDTO building = (BuildingDTO) target;

        if (this.buildingService.findByName( building.getName() ) != null) {
            errors.rejectValue("name", "buildingDTO.name.alredyExists", BUILDING_ALREDY_EXISTS);
        }
        
        if (building.getName() != null && building.getName().length()<3 ||  building.getName().length()>30) {
            errors.rejectValue("name", "buildingDTO.name.size", BUILDING_NAME_SIZE);
        }

    }
}
