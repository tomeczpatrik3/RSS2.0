package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import RoomReservationSystem.dto.reservation.EventReservationDTO;

import static RoomReservationSystem.util.RegexUtils.isValidDate;

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
        ValidationUtils.rejectIfEmpty(errors, "name", "eventReservation.name.empty", EVENT_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startDate", "eventReservation.startDate.empty", START_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endDate", "eventReservation.endDate.empty", END_TIME_EMPTY);

        EventReservationDTO reservation = (EventReservationDTO)target;     
        
        /*Esemény nevének validálása*/
        if (reservation.getName()!= null && reservation.getName().length() < 3 || reservation.getName().length() > 30 ) {
            errors.rejectValue("eventName", "eventReservation.eventName.size", EVENT_NAME_SIZE);
        }
   
        /*Esemény dátumának validálása*/
        if (reservation.getStartDate() != null && !isValidDate(reservation.getStartDate())) {
            errors.rejectValue("startDate", "eventReservation.startDate.invalidFormat", DATE_INVALID_FORMAT);
        }
         
        if (reservation.getEndDate() != null && !isValidDate(reservation.getEndDate())) {
            errors.rejectValue("endDate", "eventReservation.endDate.invalidFormat", DATE_INVALID_FORMAT);
        }
    }
}
