package RoomReservationSystem.validation;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.service.BuildingService;

import static RoomReservationSystem.config.ErrorMessageConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az épületek adatainak ellenőrzését végző osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class BuildingValidator implements Validator {

    @Autowired
    BuildingService buildingService;

    /**
     * A függvény amely az objektum típusát
     *
     * @param clazz Az objektum
     * @return Igaz, ha az objektum megfelelő típusú, hamis egyébként
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == BuildingDTO.class;
    }

    /**
     * A validálást végző függvény
     *
     * @param target Az objektum amit validálunk
     * @param errors A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "buildingDTO.name.empty", BUILDING_NAME_EMPTY);

        BuildingDTO building = (BuildingDTO) target;

        /*Épület nevének validálása*/
        if (building.getName() != null && building.getName().length() < 3 || building.getName().length() > 30) {
            errors.rejectValue("name", "buildingDTO.name.size", BUILDING_NAME_SIZE);
        }
    }
}
