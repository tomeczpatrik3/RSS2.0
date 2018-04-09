package RoomReservationSystem.validation;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.service.SemesterService;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import static RoomReservationSystem.util.DateUtils.isBefore;
import static RoomReservationSystem.util.RegexUtils.isValidDate;
import static RoomReservationSystem.util.RegexUtils.isValidSemester;

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
        ValidationUtils.rejectIfEmpty(errors, "name", "semester.name.empty", SEMESTER_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startDate", "semester.startDate.empty", SEMESTER_START_DATE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endDate", "semester.endDate.empty", SEMESTER_END_DATE_EMPTY);
        
        SemesterDTO semesterDTO = (SemesterDTO) target;

        /*Szemeszter létezésének ellenőrzése*/
        if (semesterService.findByName(semesterDTO.getName()) != null) {
            errors.rejectValue("name", "semester.name.alredyExists", SEMESTER_ALREDY_EXISTS);
        }
        
        /*Szemeszter nevének ellenőrzése*/
        if (semesterDTO.getName() != null && semesterDTO.getName().length() != 11 ) {
            errors.rejectValue("name", "semester.name.size", SEMESTER_NAME_SIZE);
        }

        if (semesterDTO.getName() != null && !isValidSemester(semesterDTO.getName()) ) {
            errors.rejectValue("name", "semester.name.invalidFormat", SEMESTER_INVALID_FORMAT);
        }        

        /*Dátumok ellenőrzése*/
        if (semesterDTO.getStartDate()!=null && !isValidDate(semesterDTO.getStartDate())) {
            errors.rejectValue("startDate", "semester.startDate.invalidFormat", SEMESTER_START_DATE_INVALID);
        }
        
        if (semesterDTO.getEndDate()!=null && !isValidDate(semesterDTO.getEndDate()) ) {
            errors.rejectValue("endDate", "semester.endDate.invalidFormat", SEMESTER_END_DATE_INVALID);
        }
        
        if (semesterDTO.getStartDate()!=null && semesterDTO.getEndDate()!=null && 
                !isBefore(semesterDTO.getStartDate(), semesterDTO.getEndDate()) ) {
            errors.rejectValue("startDate", "semester.startDate.invalidFormat", SEMESTER_END_DATE_BEFORE_START_DATE);
        }
    }
}
