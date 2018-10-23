package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ErrorMessageConstants.*;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
import RoomReservationSystem.dto.reservation.ReservationDTO;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A foglalások közös tulajdonságainak ellenőrzését elvégző osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class BaseReservationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ClassroomService classroomService;

    /**
     * A függvény amely az objektum típusát
     *
     * @param clazz Az objektum
     * @return Igaz, ha az objektum megfelelő típusú, hamis egyébként
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == EventReservationDTO.class;
    }

    /**
     * A validálást végző függvény
     *
     * @param target Az objektum amit validálunk
     * @param errors A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "baseReservation.username.empty", USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "building", "baseReservation.building.empty", BUILDING_NAME_EMTPY);
        ValidationUtils.rejectIfEmpty(errors, "classroom", "baseReservation.classroom.empty", ROOM_NAME_EMPTY);

        ReservationDTO reservation = (ReservationDTO) target;

        /*Felhasználónév ellenőrzése*/
        if (reservation.getUsername() != null && reservation.getUsername().length() < 5 || reservation.getUsername().length() > 30) {
            errors.rejectValue("username", "baseReservation.username.size", USERNAME_SIZE);
        }

        /*Épület nevének ellenőrzése*/
        if (reservation.getBuilding() != null && reservation.getBuilding().length() < 3 || reservation.getBuilding().length() > 30) {
            errors.rejectValue("buildingName", "baseReservation.buildingName.size", BUILDING_NAME_SIZE);
        }

        /*Terem nevének ellenőrzése*/
        if (reservation.getClassroom() != null && reservation.getClassroom().length() < 3 || reservation.getClassroom().length() > 30) {
            errors.rejectValue("roomName", "baseReservation.roomName.size", ROOM_NAME_SIZE);
        }
    }
}
