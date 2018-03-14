package RoomReservationSystem.validation;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A félévek adatainak ellenőrzését végző osztály
 * @author Tomecz Patrik
 */
@Service
public class SemesterValidator implements Validator {
    
    @Autowired
    SemesterService semesterService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == SemesterDTO.class;
    }

    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {

    }
}
