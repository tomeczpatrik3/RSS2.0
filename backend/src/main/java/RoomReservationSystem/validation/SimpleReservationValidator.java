package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import RoomReservationSystem.dto.reservation.SimpleReservationDTO;

import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.SubjectService;

import static RoomReservationSystem.util.RegexUtils.isValidDate;
import static RoomReservationSystem.util.RegexUtils.isValidSemester;
import static RoomReservationSystem.util.RegexUtils.isValidTime;
import static RoomReservationSystem.util.TimeUtils.isBefore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az egy napos, tantárgyakra vonatkozó foglalások validálását megvalósító osztály
 * @author Tomecz Patrik
 */
@Service
public class SimpleReservationValidator implements Validator {

    @Autowired
    private SemesterService semesterService;
    
    @Autowired
    private SubjectService subjectService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == SimpleReservationDTO.class;
    }
    
    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "semesterName", "simpleReservation.semesterName.empty", SEMESTER_NAME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "subjectCode", "simpleReservation.subjectCode.empty", SUBJECT_CODE_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "date", "simpleReservation.date.empty", DATE_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "startTime", "simpleReservation.startTime.empty", START_TIME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "endTime", "simpleReservation.endTime.empty", END_TIME_EMPTY);

        SimpleReservationDTO reservation = (SimpleReservationDTO)target;     
        
        /*Tantárgy kód validálása*/
        if (reservation.getSubject() != null && reservation.getSubject().getCode() != null) {
            if (reservation.getSubject().getCode().length()<4 || reservation.getSubject().getCode().length() > 10) {
                errors.rejectValue("subjectCode", "semesterReservation.subjectCode.size", SUBJECT_CODE_SIZE);
            }
            if (subjectService.findByCode(reservation.getSubject().getCode()) == null) {
                errors.rejectValue("subjectCode", "semesterReservation.subjectCode.notExists", SUBJECT_NOT_EXISTS);
            }
        } 
        
        /*Dátum validálása*/
//        if (reservation.getDate() != null && !isValidDate(reservation.getDate())) {
//            errors.rejectValue("date", "simpleReservation.date.invalidFormat", DATE_INVALID_FORMAT);
//        }
        
        /*Idők validálása*/
//        if (reservation.getStartTime() != null && !isValidTime(reservation.getStartTime())) {
//            errors.rejectValue("startTime", "simpleReservation.startTime.invalidFormat", TIME_INVALID_FORMAT);
//        }
//
//        if (reservation.getEndTime()!= null && !isValidTime(reservation.getEndTime())) {
//            errors.rejectValue("endTime", "simpleReservation.endTime.invalidFormat", TIME_INVALID_FORMAT);
//        }
//        
//        if (reservation.getStartTime() != null && reservation.getEndTime()!= null 
//                && !isBefore(reservation.getStartTime(), reservation.getEndTime()) ) {
//            errors.rejectValue("startTime", "simpleReservation.startTime.invalid", START_TIME_BEFORE_END_TIME);
//        }
    }
}