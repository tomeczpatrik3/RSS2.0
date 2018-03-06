package RoomReservationSystem.config;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationErrorMessageConstants {
    //CLASSROOM:
    public static final String CLASSROOMDTO_NAME_EMPTY = "A tanterem neve nem lehet üres!";
    public static final String CLASSROOMDTO_HAS_PC_EMPTY = "A PC nem lehet üres!";
    public static final String CLASSROOMDTO_HAS_PROJECTOR_EMPTY = "A projektor neve nem lehet üres!";
    public static final String CLASSROOMDTO_CHAIRS_EMPTY = "A székek száma nem lehet üres!";
    public static final String CLASSROOMDTO_BUILDING_EMPTY = "Az épület neve nem lehet üres!";
    public static final String CLASSROOMDTO_NAME_ALREDY_EXISTS = "Ilyen nevű tanterem már létezik!";
    public static final String CLASSROOMDTO_NAME_SIZE = "A tanterem neve nem megfelelő hosszúságú (3-30)!";
    public static final String CLASSROOMDTO_CHAIRS_VALUE = "A székek száma nem nagyobb mint nulla!";
    public static final String CLASSROOMDTO_BUILDING_SIZE = "Az épület neve nem megfelelő hosszúságú (3-30)!";
    
    
    //RESERVATION:
    
    //SUBJECT:
    
    //USER:
    
    
    public static String concatErrors(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        errors.append("Hiba történt: \n");
        for (ObjectError err : bindingResult.getAllErrors()) {
           errors.append(err.getDefaultMessage());
        }
        return errors.toString();
    }
}
