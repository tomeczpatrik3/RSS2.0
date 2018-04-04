package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.EventReservationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Tomecz Patrik
 */
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
        ValidationUtils.rejectIfEmpty(errors, "eventName", "eventReservation.eventName.empty", EVENT_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "date", "eventReservation.date.empty", DATE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startTime", "eventReservation.startTime.empty", START_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endTime", "eventReservation.endTime.empty", END_TIME_EMPTY);

        EventReservationDTO reservation = (EventReservationDTO)target;     

        
    }
}
