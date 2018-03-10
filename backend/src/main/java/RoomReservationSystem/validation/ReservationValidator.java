package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.service.impl.BuildingServiceImpl;
import RoomReservationSystem.service.impl.ClassroomServiceImpl;
import RoomReservationSystem.service.impl.SubjectServiceImpl;
import RoomReservationSystem.service.impl.UserServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ReservationValidator implements Validator {
    
    private static final Pattern DATE_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Pattern TIME_REGEX = Pattern.compile("^([01][0-9]|2[0-3]):[0-5][0-9]$");
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private SubjectServiceImpl subjectService;
            
    @Autowired
    private ClassroomServiceImpl classroomService;
    
    /*
        Két óó:pp típusú string esetén ellenőrzi, hogy az első
        korábban van-e mint a második
        (Formátum ellenőrzés a fgv hívás előtt történik)
    */
    private boolean isBefore(String t1, String t2) {
        int hour1, hour2, min1, min2;
        
        hour1 = Integer.parseInt(t1.split(":")[0]);
        min1 = Integer.parseInt(t1.split(":")[1]);
        
        hour2 = Integer.parseInt(t2.split(":")[0]);
        min2 = Integer.parseInt(t2.split(":")[1]);
        
        if ( hour1 < hour2 ) {
            return true;
        }
        else if ( hour1 == hour2 && min1 < min2) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == ReservationDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "reservation.username.empty", USER_USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "subjectCode", "reservation.subjectCode.empty", SUBJECT_CODE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "room", "reservation.room.empty", CLASSROOM_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "day", "reservation.day.empty", RESERVATION_DAY_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startTime", "reservation.startTime.empty", RESERVATION_START_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endTime", "reservation.endTime.empty", RESERVATION_END_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startDate", "reservation.startDate.empty", RESERVATION_START_DATE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endDate", "reservation.endDate.empty", RESERVATION_END_DATE_EMPTY);

        ReservationDTO res = (ReservationDTO)target;     

        //User:
        if ( this.userService.findByUsername(res.getUsername()) == null ) {
            errors.rejectValue("username", "reservation.username.notExists", USER_NOT_EXISTS);
        }
        
        if (res.getUsername() != null && res.getUsername().length() < 5 ||
                res.getUsername().length() > 30) {
            errors.rejectValue("username", "reservation.username.size", USER_USERNAME_SIZE);
        }
        
        if (res.getUsername() != null && res.getUsername().contains(" ")) {
            errors.rejectValue("username", "reservation.username.space", USER_USERNAME_SPACE);
        }
        
        //Subject:
        if (subjectService.findByCode(res.getSubjectCode()) == null) {
            errors.rejectValue("subjectCode", "reservation.subjectCode.notExists", SUBJECT_NOT_EXISTS);
        }
        
        if (res.getSubjectName() != null && res.getSubjectName().length() < 5 ||
                res.getSubjectName().length() > 30) {
            errors.rejectValue("subjectName", "reservation.subjectName.size", SUBJECT_NAME_SIZE);
        }
        
        //Classroom:
        if (this.classroomService.findByName( res.getRoom() ) == null) {
            errors.rejectValue("room", "reservation.room.notExists", CLASSROOM_NOT_EXISTS);
        }
        
        if (res.getRoom() != null && res.getRoom().length()<3 ||  res.getRoom().length()>30) {
            errors.rejectValue("room", "reservation.room.size", CLASSROOM_NAME_SIZE);
        }
        
        //Own attributes:
        if (res.getDay() != null && res.getDay().length() < 3 || res.getDay().length()>12) {
            errors.rejectValue("day", "reservation.day.size", RESERVATION_DAY_SIZE);
        }
      
        //Date:
        if (res.getStartDate() != null && !DATE_REGEX.matcher(res.getStartDate()).matches()) {
            errors.rejectValue("startDate", "reservation.startDate.invalid", RESERVATION_START_DATE_INVALID);
        }
        
        if (res.getEndDate() != null && !DATE_REGEX.matcher(res.getEndDate()).matches()) {
            errors.rejectValue("endDate", "reservation.endDate.invalid", RESERVATION_END_DATE_INVALID);
        }
        
        //Dátummá alakítás:
        try {
            Date startDate =  dateFormat.parse(res.getStartDate());
            Date endDate = dateFormat.parse(res.getEndDate());
            
            if (!startDate.before(endDate)) {
                errors.rejectValue("endDate", "reservation.endDate.beforeStartDate", RESERVATION_END_DATE_BEFORE_START_DATE);
            }   
        } catch (ParseException ex) {
            errors.rejectValue("startDate", "reservation.startDate.invalid", RESERVATION_START_DATE_INVALID);
            errors.rejectValue("endDate", "reservation.endDate.invalid", RESERVATION_END_DATE_INVALID);
        }
        
        if (res.getStartTime() != null && !TIME_REGEX.matcher(res.getStartTime()).matches()) {
            errors.rejectValue("startTime", "reservation.startTime.invalid", RESERVATION_START_TIME_INVALID);
        }
        
        if (res.getEndTime() != null && !TIME_REGEX.matcher(res.getEndTime()).matches()) {
            errors.rejectValue("endTime", "reservation.endTime.invalid", RESERVATION_END_TIME_INVALID);
        }
        
        if ( TIME_REGEX.matcher(res.getStartTime()).matches() && 
                TIME_REGEX.matcher(res.getEndTime()).matches() &&
                !isBefore( res.getStartTime(), res.getEndTime() ) ) {
            errors.rejectValue("endTime", "reservation.endTime.beforeStartTime", RESERVATION_END_TIME_BEFORE_START_TIME);
        }
    }
}
