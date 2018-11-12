package RoomReservationSystem.validation;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.service.SubjectService;

import static RoomReservationSystem.constants.ErrorMessageConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A tantárgyak adatainak ellenőrzését végző osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class SubjectValidator implements Validator {

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
        return clazz == SubjectDTO.class;
    }

    /**
     * A validálást végző függvény
     *
     * @param target Az objektum amit validálunk
     * @param errors A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "subject.name.empty", SUBJECT_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "code", "subject.code.empty", SUBJECT_CODE_EMPTY);

        SubjectDTO subject = (SubjectDTO) target;

        /*Tantárgy kód validálása*/
        if (subject.getCode() != null && subject.getCode().length() < 4 || subject.getCode().length() > 15) {
            errors.rejectValue("subjectCode", "subject.code.size", SUBJECT_CODE_SIZE);
        }

        /*Tantárgy nevének validálása*/
        if (subject.getName() != null && subject.getName().length() < 5
                || subject.getName().length() > 30) {
            errors.rejectValue("name", "subject.name.size", SUBJECT_NAME_SIZE);
        }
    }
}
