package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.BaseReservationDTO;
import RoomReservationSystem.dto.EventReservationDTO;
import RoomReservationSystem.service.BuildingService;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Tomecz Patrik
 */
public class BaseReservationValidator implements Validator {
    @Autowired
    private UserService userService;
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private ClassroomService classroomService;
    
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
        ValidationUtils.rejectIfEmpty(errors, "username", "baseReservation.username.empty", USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "buildingName", "baseReservation.buildingName.empty", BUILDING_NAME_EMTPY);
        ValidationUtils.rejectIfEmpty(errors, "roomName", "baseReservation.roomName.empty", ROOM_NAME_EMPTY);
        //ValidationUtils.rejectIfEmpty(errors, "note", "eventReservation.note.empty", NOTE_EMPTY);
        
        BaseReservationDTO reservation = (BaseReservationDTO)target;     
        
        if (reservation.getUsername() != null && userService.findByUsername(reservation.getUsername()) == null) {
            errors.rejectValue("username", "baseReservation.username.notExists", USER_NOT_EXISTS);
        }
        
        if (reservation.getUsername() != null && reservation.getUsername().length() < 5 || reservation.getUsername().length() > 30 ) {
            errors.rejectValue("username", "baseReservation.username.size", USERNAME_SIZE);
        }
        
        if (reservation.getBuildingName() != null && buildingService.findByName(reservation.getBuildingName()) == null) {
            errors.rejectValue("buildingName", "baseReservation.buildingName.notExists", BUILDING_NOT_EXISTS);
        }
        
        if (reservation.getBuildingName() != null && reservation.getBuildingName().length() < 3 || reservation.getBuildingName().length() > 30 ) {
            errors.rejectValue("buildingName", "baseReservation.buildingName.size", BUILDING_NAME_SIZE);
        }
        
        if (reservation.getRoomName() != null && reservation.getBuildingName() != null 
                && classroomService.findByNameAndBuildingName(reservation.getRoomName(), reservation.getBuildingName()) == null) {
            errors.rejectValue("roomName", "baseReservation.roomName.notExists", ROOM_NOT_EXISTS);
        }
        
        if (reservation.getRoomName() != null && reservation.getRoomName().length() < 3 || reservation.getRoomName().length() > 30 ) {
            errors.rejectValue("roomName", "baseReservation.roomName.size", ROOM_NAME_SIZE);
        }
    }
}
