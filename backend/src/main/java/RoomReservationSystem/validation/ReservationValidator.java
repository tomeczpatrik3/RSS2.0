package RoomReservationSystem.validation;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A foglalások adatainak ellenőrzését végző osztály
 * @author Tomecz Patrik
 */
@Service
public class ReservationValidator implements Validator {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SubjectService subjectService;
            
    @Autowired
    private ClassroomService classroomService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == ReservationDTO.class;
    }

    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "reservation.username.empty", USER_USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "subjectCode", "reservation.subjectCode.empty", SUBJECT_CODE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "roomName", "reservation.roomName.empty", CLASSROOM_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "day", "reservation.day.empty", RESERVATION_DAY_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startTime", "reservation.startTime.empty", RESERVATION_START_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endTime", "reservation.endTime.empty", RESERVATION_END_TIME_EMPTY);

        ReservationDTO res = (ReservationDTO)target;     

        
    }
}
