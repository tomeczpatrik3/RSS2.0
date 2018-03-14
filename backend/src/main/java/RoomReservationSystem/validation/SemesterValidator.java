package RoomReservationSystem.validation;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.service.SemesterService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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
    private static final Pattern DATE_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
    private static final Pattern NAME_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{4}-[\\d]{1}$");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    
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

        if (semesterService.findByName(semesterDTO.getName()) != null) {
            errors.rejectValue("name", "semester.name.alredyExists", SEMESTER_ALREDY_EXISTS);
        }
        
        if (semesterDTO.getName() != null && semesterDTO.getName().length() != 11 ) {
            errors.rejectValue("name", "semester.name.size", SEMESTER_NAME_SIZE);
        }

        if (semesterDTO.getName() != null && !NAME_REGEX.matcher( semesterDTO.getName() ).matches() ) {
            errors.rejectValue("name", "semester.name.invalid", SEMESTER_NAME_INVALID);
        }        

        if (semesterDTO.getStartDate()!=null && !DATE_REGEX.matcher( semesterDTO.getStartDate() ).matches() ) {
            errors.rejectValue("startDate", "semester.startDate.invalid", SEMESTER_START_DATE_INVALID);
        }
        
        if (semesterDTO.getEndDate()!=null && !DATE_REGEX.matcher( semesterDTO.getEndDate() ).matches() ) {
            errors.rejectValue("endDate", "semester.endDate.invalid", SEMESTER_END_DATE_INVALID);
        }
        
        //TODO: 
        try {
            Date startDate =  dateFormat.parse(semesterDTO.getStartDate());
            Date endDate = dateFormat.parse(semesterDTO.getEndDate());
            
            if (!startDate.before(endDate)) {
                errors.rejectValue("endDate", "semester.endDate.beforeStartDate", SEMESTER_END_DATE_BEFORE_START_DATE);
            }   
        } catch (ParseException ex) {
            errors.rejectValue("startDate", "semester.startDate.invalid", SEMESTER_START_DATE_INVALID);
            errors.rejectValue("endDate", "semester.endDate.invalid", SEMESTER_END_DATE_INVALID);
        }
        
    }
}
