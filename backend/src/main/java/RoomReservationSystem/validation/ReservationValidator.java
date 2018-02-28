package RoomReservationSystem.validation;

import RoomReservationSystem.model.Reservation;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ReservationValidator implements Validator {
    
    private static final Pattern DATE_REGEX = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    
    private static final Pattern TIME_REGEX = Pattern.compile("^([01][0-9]|2[0-3]):[0-5][0-9]$");
    
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
      return clazz == Reservation.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startDate", "reservation.startDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "reservation.endDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "subjectName", "reservation.subjectName.empty");
        ValidationUtils.rejectIfEmpty(errors, "username", "reservation.username.empty");
        ValidationUtils.rejectIfEmpty(errors, "roomName", "reservation.roomName.empty");

        Reservation res = (Reservation)target;     

        if (res.getStartDate() != null && !DATE_REGEX.matcher(dateFormat.format(res.getStartDate())).matches()) {
            errors.rejectValue("startDate", "reservation.startDate.invalid");
        }
        
        if (res.getEndDate() != null && !DATE_REGEX.matcher(dateFormat.format(res.getEndDate())).matches()) {
            errors.rejectValue("endDate", "reservation.endDate.invalid");
        }
        
        /*
        if (res.getEndDate().before( res.getStartDate() )) {
            errors.rejectValue("endDate", "reservation.endDate.beforeStartDate");
        }
        */
        
        if (res.getStartTime() != null && !TIME_REGEX.matcher(res.getStartTime()).matches()) {
            errors.rejectValue("startTime", "reservation.startTime.invalid");
        }
        
        if (res.getEndTime() != null && !TIME_REGEX.matcher(res.getEndTime()).matches()) {
            errors.rejectValue("endTime", "reservation.endTime.invalid");
        }
        
        if ( TIME_REGEX.matcher(res.getStartTime()).matches() && 
                TIME_REGEX.matcher(res.getEndTime()).matches() &&
                !isBefore( res.getStartTime(), res.getEndTime() ) ) {
            errors.rejectValue("endTime", "reservation.endTime.beforeStartTime");
        }
    }
}
