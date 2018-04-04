package RoomReservationSystem.config;

import org.springframework.validation.BindingResult;

/**
 * A validálással kapcsolatos hibaüzeneteket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class ValidationErrorMessageConstants {
    //CLASSROOM:
    public static final String CLASSROOM_EMPTY = "A tantermet meg kell adni!";
    public static final String CLASSROOM_NAME_EMPTY = "A tanterem neve nem lehet üres!";
    public static final String CLASSROOM_HAS_PC_EMPTY = "A PC nem lehet üres!";
    public static final String CLASSROOM_HAS_PROJECTOR_EMPTY = "A projektor neve nem lehet üres!";
    public static final String CLASSROOM_CHAIRS_EMPTY = "A székek száma nem lehet üres!";
    public static final String CLASSROOM_BUILDING_EMPTY = "Az épület neve nem lehet üres!";
    public static final String CLASSROOM_ALREDY_EXISTS = "Ez a tanterem már létezik!";
    public static final String CLASSROOM_NOT_EXISTS = "Ilyen nevű tanterem nem létezik!";
    public static final String CLASSROOM_NAME_SIZE = "A tanterem neve nem megfelelő hosszúságú (3-30)!";
    public static final String CLASSROOM_CHAIRS_VALUE = "A székek száma nem nagyobb mint nulla!";
    
    //BUILDING:
    public static final String BUILDING_EMPTY = "Az épületet meg kell adni!";
    public static final String BUILDING_ALREDY_EXISTS = "Ezzel a névvel már létezik épület!";
    public static final String BUILDING_NAME_EMPTY = "Az épület neve nem lehet üres!";
    
    //--------------
    public static final String USERNAME_EMPTY = "A felhasználónév nem lehet üres!";
    public static final String BUILDING_NAME_EMTPY = "Az épület neve nem lehet üres!";
    public static final String ROOM_NAME_EMPTY = "A terem neve nem lehet üres!";
    public static final String NOTE_EMPTY = "A megjegyzés nem lehet üres!";
    public static final String EVENT_NAME_EMPTY = "Az esemény neve nem lehet üres!";
    public static final String DATE_EMPTY = "Az esemény dátuma nem lehet üres!";
    public static final String START_TIME_EMPTY = "Az esemény kezdete nem lehet üres!";
    public static final String END_TIME_EMPTY = "Az esemény vége nem lehet üres!";
    
    public static final String USERNAME_SIZE = "A felhasználónév nem megfelelő hosszúságú (5-30)!";
    public static final String USER_NOT_EXISTS = "Nem létezik ezzel a felhasználónévvel rendelkező felhasználó!";
    
    public static final String BUILDING_NOT_EXISTS = "Nem létezik ilyen nevű épület!";
    public static final String BUILDING_NAME_SIZE = "Az épület neve nem megfelelő hosszúságú (3-30)!";
        
    public static final String ROOM_NOT_EXISTS = "Ilyen nevű terem nem létezik!";
    public static final String ROOM_NAME_SIZE = "A terem neve nem megfelelő hosszúságú (3-30)!";
      
    
    //-------------
    public static final String RESERVATION_NOT_EXISTS = "Ilyen foglalás nem létezik!";
    public static final String RESERVATION_ALREDY_EXISTS = "Ilyen foglalás már létezik!";
    public static final String RESERVATION_DATES_EMPTY = "A foglaláshoz tartozó időpontokat meg kell adni!";
    
    
    
    //SUBJECT:
    public static final String SUBJECT_EMPTY = "A tantárgyat meg kell adni!";
    public static final String SUBJECT_ALREDY_EXISTS = "Ezzel a kóddal már létezik tantárgy!";
    public static final String SUBJECT_NOT_EXISTS = "Nem létezik ilyen tantárgy!";
    public static final String SUBJECT_NAME_SIZE = "A tantárgy neve nem megfelelő hosszúságú (3-30)!";
    public static final String SUBJECT_NAME_EMPTY = "A tantárgy neve nem lehet üres!";  
    public static final String SUBJECT_CODE_SIZE = "A tantárgy kódja nem megfelelő hosszúságú (4-10)!";
    public static final String SUBJECT_CODE_EMPTY = "A tantárgy kódja nem lehet üres!";    
    
    //USER:
    public static final String USER_EMTPY = "A felhasználót meg kell adni!";
    public static final String USER_USERNAME_EMPTY = "A felhasználónév nem lehet üres!";
    public static final String USER_NAME_EMPTY = "A név nem lehet üres!";
    public static final String USER_PASSWORD_EMPTY = "A jelszó nem lehet üres!";
    public static final String USER_EMAIL_EMPTY = "Az e-mail cím nem lehet üres!";
    public static final String USER_ALREDY_EXISTS = "Ez a felhasználónév már foglalt!";
    public static final String USER_EMAIL_ALREDY_EXISTS = "Ez az e-mail cím már használatban van!";
    public static final String USER_NAME_SIZE = "A név nem megfelelő hosszúságú (5-30)!";
     public static final String USER_USERNAME_SPACE = "A felhasználónév nem tartalmazhat szóközt!";
    public static final String USER_PASSWORD_SPACE = "A jelszó nem megfelelő hosszúságú (5-30)!";
    public static final String USER_PASSWORD_SIZE = "A jelszó nem megfelelő hosszúságú (5-50)!";
    public static final String USER_EMAIL_SIZE = "A e-mail cím nem megfelelő hosszúságú (5-50)!";
    public static final String USER_EMAIL_INVALID = "Az e-mail cím formátuma nem megfelelő!";
    
    //SEMESTER:
    public static final String SEMESTER_EMPTY = "A félévet meg kell adni!";
    public static final String SEMESTER_NAME_EMPTY = "A félév neve nem lehet üres!";
    public static final String SEMESTER_START_DATE_EMPTY = "A félév kezdetének dátuma nem lehet üres!";
    public static final String SEMESTER_END_DATE_EMPTY = "A félév végének dátuma nem lehet üres!";
    public static final String SEMESTER_ALREDY_EXISTS = "Ezzel a névvel már létezik félév!";
    public static final String SEMESTER_NAME_SIZE = "A félév nevének hossza 11 karakter kell hogy legyen (pl.: 2017-2018-2)!";
    public static final String SEMESTER_NAME_INVALID = "A félév neve nem megfelelő formátumú (pl.: 2017-2018-2)!";
    public static final String SEMESTER_START_DATE_INVALID = "A félév elejének dátuma nem megfelelő formátumú!";
    public static final String SEMESTER_END_DATE_INVALID = "A félév végének dátuma nem megfelelő formátumú!";
    public static final String SEMESTER_END_DATE_BEFORE_START_DATE = "A félév végének dátuma nem lehet korábbi, mint a kezdeti dátum!";
    
    
    
    /**
     * A hibaüzenetek konkatenálását végző függvény
     * @param   bindingResult   A validálás eredménye
     * @return                  A hibaüzenet(ek) szövegként
     */
    public static String concatErrors(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        errors.append("Hiba történt: \n");
        bindingResult.getAllErrors().forEach((err) -> {
            errors.append(err.getDefaultMessage());
        });
        return errors.toString();
    }
}
