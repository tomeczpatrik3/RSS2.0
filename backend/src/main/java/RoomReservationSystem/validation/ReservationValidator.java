package RoomReservationSystem.validation;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;

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
        ValidationUtils.rejectIfEmpty(errors, "semester", "reservation.semester.empty", SEMESTER_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "user", "reservation.user.empty", USER_EMTPY);
        ValidationUtils.rejectIfEmpty(errors, "subject", "reservation.subject.empty", SUBJECT_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "classroom", "reservation.classroom.empty", CLASSROOM_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "dates", "reservation.dates.empty", RESERVATION_DATES_EMPTY);

        ReservationDTO res = (ReservationDTO)target;     

        
    }
}
