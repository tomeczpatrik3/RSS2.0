package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import RoomReservationSystem.dto.reservation.EventReservationDTO;


import static RoomReservationSystem.util.RegexUtils.isValidDate;
import static RoomReservationSystem.util.RegexUtils.isValidTime;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az események foglalásának validálását megvalósító osztály
 * @author Tomecz Patrik
 */
@Service
public class EventReservationValidator implements Validator {

    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == EventReservationDTO.class;
    }
    
    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "eventName", "eventReservation.eventName.empty", EVENT_NAME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "date", "eventReservation.date.empty", DATE_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "startTime", "eventReservation.startTime.empty", START_TIME_EMPTY);
//        ValidationUtils.rejectIfEmpty(errors, "endTime", "eventReservation.endTime.empty", END_TIME_EMPTY);

        EventReservationDTO reservation = (EventReservationDTO)target;     
        
        /*Esemény nevének validálása*/
        if (reservation.getName()!= null && reservation.getName().length() < 3 || reservation.getName().length() > 30 ) {
            errors.rejectValue("eventName", "eventReservation.eventName.size", EVENT_NAME_SIZE);
        }
   
        /*Esemény dátumának validálása*/
//        if (reservation.getDate() != null && !isValidDate(reservation.getDate())) {
//            errors.rejectValue("date", "eventReservation.date.invalidFormat", DATE_INVALID_FORMAT);
//        }
        
        /*Az idők validálása*/
//        if (reservation.getStartTime() != null && !isValidTime(reservation.getStartTime())) {
//            errors.rejectValue("startTime", "eventReservation.startTime.invalidFormat", TIME_INVALID_FORMAT);
//        }
//
//        if (reservation.getEndTime()!= null && !isValidTime(reservation.getEndTime())) {
//            errors.rejectValue("endTime", "eventReservation.endTime.invalidFormat", TIME_INVALID_FORMAT);
//        }        
    }
}
