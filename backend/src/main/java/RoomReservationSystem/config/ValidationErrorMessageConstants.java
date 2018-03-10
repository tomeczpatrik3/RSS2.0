package RoomReservationSystem.config;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationErrorMessageConstants {
    //CLASSROOM:
    public static final String CLASSROOM_NAME_EMPTY = "A tanterem neve nem lehet üres!";
    public static final String CLASSROOM_HAS_PC_EMPTY = "A PC nem lehet üres!";
    public static final String CLASSROOM_HAS_PROJECTOR_EMPTY = "A projektor neve nem lehet üres!";
    public static final String CLASSROOM_CHAIRS_EMPTY = "A székek száma nem lehet üres!";
    public static final String CLASSROOM_BUILDING_EMPTY = "Az épület neve nem lehet üres!";
    public static final String CLASSROOM_ALREDY_EXISTS = "Ilyen nevű tanterem már létezik!";
    public static final String CLASSROOM_NOT_EXISTS = "Ilyen nevű tanterem nem létezik!";
    public static final String CLASSROOM_NAME_SIZE = "A tanterem neve nem megfelelő hosszúságú (3-30)!";
    public static final String CLASSROOM_CHAIRS_VALUE = "A székek száma nem nagyobb mint nulla!";
    
    //BUILDING:
    public static final String BUILDING_ALREDY_EXISTS = "Ezzel a névvel már létezik épület!";
    public static final String BUILDING_NOT_EXISTS = "Nem létezik ilyen épület!";
    public static final String BUILDING_NAME_SIZE = "Az épület neve nem megfelelő hosszúságú (3-30)!";
    public static final String BUILDING_NAME_EMPTY = "Az épület neve nem lehet üres!";
    
    //RESERVATION:
    public static final String RESERVATION_DAY_EMPTY = "A nap nem lehet üres!";
    public static final String RESERVATION_START_TIME_EMPTY = "A kezdeti idő nem lehet üres!";
    public static final String RESERVATION_END_TIME_EMPTY = "A befejezési idő nem lehet üres!";
    public static final String RESERVATION_START_DATE_EMPTY = "A kezdeti dátum nem lehet üres!";
    public static final String RESERVATION_END_DATE_EMPTY = "A befejezési dátum nem lehet üres!";
    public static final String RESERVATION_DAY_SIZE = "A nap nem megfelelő hosszúságú (3-12)!";
    public static final String RESERVATION_START_DATE_INVALID = "A kezdeti dátum nem megfelelő formátumú!";
    public static final String RESERVATION_END_DATE_INVALID = "A befejezési dátum nem megfelelő formátumú!";
    public static final String RESERVATION_END_DATE_BEFORE_START_DATE = "A befejezési dátum nem lehet korábbi, mint a kezdési dátum!";
    public static final String RESERVATION_START_TIME_INVALID = "A kezdeti idő nem megfelelő formátumú!";
    public static final String RESERVATION_END_TIME_INVALID = "A befejezési idő nem megfelelő formátumú!";
    public static final String RESERVATION_END_TIME_BEFORE_START_TIME = "A befejezési idő nem lehet korábban, mint a kezdési idő!";
    
    //SUBJECT:
    public static final String SUBJECT_ALREDY_EXISTS = "Ezzel a kóddal már létezik tantárgy!";
    public static final String SUBJECT_NOT_EXISTS = "Nem létezik ilyen tantárgy!";
    public static final String SUBJECT_NAME_SIZE = "A tantárgy neve nem megfelelő hosszúságú (3-30)!";
    public static final String SUBJECT_NAME_EMPTY = "A tantárgy neve nem lehet üres!";  
    public static final String SUBJECT_CODE_SIZE = "A tantárgy kódja nem megfelelő hosszúságú (4-10)!";
    public static final String SUBJECT_CODE_EMPTY = "A tantárgy kódja nem lehet üres!";    
    
    //USER:
    public static final String USER_USERNAME_EMPTY = "A felhasználónév nem lehet üres!";
    public static final String USER_NAME_EMPTY = "A név nem lehet üres!";
    public static final String USER_PASSWORD_EMPTY = "A jelszó nem lehet üres!";
    public static final String USER_EMAIL_EMPTY = "Az e-mail cím nem lehet üres!";
    public static final String USER_ALREDY_EXISTS = "Ez a felhasználónév már foglalt!";
    public static final String USER_NOT_EXISTS = "Nem létezik ezzel a felhasználónévvel rendelkező felhasználó!";
    public static final String USER_EMAIL_ALREDY_EXISTS = "Ez az e-mail cím már használatban van!";
    public static final String USER_NAME_SIZE = "A név nem megfelelő hosszúságú (5-30)!";
    public static final String USER_USERNAME_SIZE = "A felhasználónév nem megfelelő hosszúságú (5-30)!";
    public static final String USER_USERNAME_SPACE = "A felhasználónév nem tartalmazhat szóközt!";
    public static final String USER_PASSWORD_SPACE = "A jelszó nem megfelelő hosszúságú (5-30)!";
    public static final String USER_PASSWORD_SIZE = "A jelszó nem megfelelő hosszúságú (5-50)!";
    public static final String USER_EMAIL_SIZE = "A e-mail cím nem megfelelő hosszúságú (5-50)!";
    public static final String USER_EMAIL_INVALID = "Az e-mail cím formátuma nem megfelelő!";       
    
    public static String concatErrors(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        errors.append("Hiba történt: \n");
        for (ObjectError err : bindingResult.getAllErrors()) {
           errors.append(err.getDefaultMessage());
        }
        return errors.toString();
    }
}
