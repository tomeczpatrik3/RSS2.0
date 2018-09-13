package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;
import RoomReservationSystem.dto.reservation.ClassReservationDTO;

import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.SubjectService;
import static RoomReservationSystem.util.DateUtils.areBefore;
import static RoomReservationSystem.util.RegexUtils.areValidDates;
import static RoomReservationSystem.util.RegexUtils.isValidSemester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A tanórákra vonatkozó foglalások validálását megvalósító osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class ClassReservationValidator implements Validator {

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private SubjectService subjectService;

    /**
     * A függvény amely az objektum típusát
     *
     * @param clazz Az objektum
     * @return Igaz, ha az objektum megfelelő típusú, hamis egyébként
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ClassReservationDTO.class;
    }

    /**
     * A validálást végző függvény
     *
     * @param target Az objektum amit validálunk
     * @param errors A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "semester", "classReservation.semester.empty", SEMESTER_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "subjectCode", "classReservation.subjectCode.empty", SUBJECT_CODE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "startDates", "classReservation.startDates.empty", START_TIME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "endDates", "classReservation.endDates.empty", END_TIME_EMPTY);

        ClassReservationDTO reservation = (ClassReservationDTO) target;

        /*Szemeszter nevének ellenőrzése*/
        if (reservation.getSemester() != null && reservation.getSemester().length() != 11) {
            errors.rejectValue("semester", "classReservation.semester.size", SEMESTER_NAME_SIZE);
        }

        if (reservation.getSemester() != null && !isValidSemester(reservation.getSemester())) {
            errors.rejectValue("semester", "classReservation.semester.invalidFormat", SEMESTER_INVALID_FORMAT);
        }

        /*Tantárgy kód validálása*/
        if (reservation.getSubjectCode() != null && reservation.getSubjectCode().length() < 4 || reservation.getSubjectCode().length() > 10) {
            errors.rejectValue("subjectCode", "classReservation.subjectCode.size", SUBJECT_CODE_SIZE);
        }

        /*Dátumok validálása*/
        if (reservation.getStartDates() != null && !areValidDates(reservation.getStartDates())) {
            errors.rejectValue("startDates", "classReservation.startDates.invalidFormat", DATE_INVALID_FORMAT);
        }

        if (reservation.getEndDates() != null && !areValidDates(reservation.getEndDates())) {
            errors.rejectValue("endDates", "classReservation.endDates.invalidFormat", DATE_INVALID_FORMAT);
        }

        if (reservation.getStartDates() != null && reservation.getEndDates() != null && !areBefore(reservation.getStartDates(), reservation.getEndDates())) {
            errors.rejectValue("endDates", "classReservation.endDates.endDateBeforeStartDate", END_DATE_BEFORE_START_DATE);
        }
    }
}
