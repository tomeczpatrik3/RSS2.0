package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import RoomReservationSystem.dto.reservation.SemesterReservationDTO;

import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.SubjectService;

import static RoomReservationSystem.util.DayUtils.isValidDay;
import static RoomReservationSystem.util.RegexUtils.isValidSemester;
import static RoomReservationSystem.util.RegexUtils.isValidTime;
import static RoomReservationSystem.util.TimeUtils.isBefore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az egész szemeszterre vonatkozó foglalások validálását megvalósító osztály
 * @author Tomecz Patrik
 */
@Service
public class SemesterReservationValidator implements Validator {

    @Autowired
    private SemesterService semesterService;
    
    @Autowired
    private SubjectService subjectService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == SemesterReservationDTO.class;
    }
    
    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "semesterName", "semesterReservation.semesterName.empty", SEMESTER_NAME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "subjectCode", "semesterReservation.subjectCode.empty", SUBJECT_CODE_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "day", "semesterReservation.day.empty", DAY_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "startTime", "semesterReservation.startTime.empty", START_TIME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "endTime", "semesterReservation.endTime.empty", END_TIME_EMPTY);

        SemesterReservationDTO reservation = (SemesterReservationDTO)target;     

        /*Szemeszter validálása*/
        if (reservation.getSemester() != null && reservation.getSemester().getName() != null ) {
            if (reservation.getSemester().getName().length() != 11) {
                errors.rejectValue("semesterName", "semesterReservation.semesterName.size", SEMESTER_NAME_SIZE);
            }
            if (!isValidSemester(reservation.getSemester().getName())) {
                errors.rejectValue("semesterName", "semesterReservation.semesterName.invalidFormat", SEMESTER_NOT_EXISTS);
            }
            if (semesterService.findByName(reservation.getSemester().getName()) == null) {
                errors.rejectValue("semesterName", "semesterReservation.semesterName.notExists", SEMESTER_NOT_EXISTS);
            }
        }
        
        /*Tantárgy kód validálása*/
        if (reservation.getSubject() != null && reservation.getSubject().getCode() != null) {
            if (reservation.getSubject().getCode().length()<4 || reservation.getSubject().getCode().length() > 10) {
                errors.rejectValue("subjectCode", "semesterReservation.subjectCode.size", SUBJECT_CODE_SIZE);
            }
            if (subjectService.findByCode(reservation.getSubject().getCode()) == null) {
                errors.rejectValue("subjectCode", "semesterReservation.subjectCode.notExists", SUBJECT_NOT_EXISTS);
            }
        }
        
        
        /*Nap validálása*/
//        if (reservation.getDay() != null && !isValidDay(reservation.getDay())) {
//            errors.rejectValue("day", "semesterReservation.day.invalidFormat", DAY_INVALID_FORMAT);
//        }
        
       /*Az idők validálása*/
//        if (reservation.getStartTime() != null && !isValidTime(reservation.getStartTime())) {
//            errors.rejectValue("startTime", "semesterReservation.startTime.invalidFormat", TIME_INVALID_FORMAT);
//        }
//
//        if (reservation.getEndTime()!= null && !isValidTime(reservation.getEndTime())) {
//            errors.rejectValue("endTime", "semesterReservation.endTime.invalidFormat", TIME_INVALID_FORMAT);
//        }  
//
//        if (reservation.getStartTime() != null && reservation.getEndTime()!= null 
//                && !isBefore(reservation.getStartTime(), reservation.getEndTime()) ) {
//            errors.rejectValue("startTime", "semesterReservation.startTime.invalid", START_TIME_BEFORE_END_TIME);
//        }        
    }
}