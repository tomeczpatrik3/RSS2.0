package RoomReservationSystem.config;

/**
 * A validálással kapcsolatos hibaüzeneteket tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
public class ErrorMessageConstants {
    public static final String USERNAME_EMPTY = "A felhasználónév nem lehet üres!";
    public static final String BUILDING_NAME_EMTPY = "Az épület neve nem lehet üres!";
    public static final String ROOM_NAME_EMPTY = "A terem neve nem lehet üres!";
    public static final String NOTE_EMPTY = "A megjegyzés nem lehet üres!";

    public static final String EVENT_NAME_EMPTY = "Az esemény neve nem lehet üres!";
    public static final String DATE_EMPTY = "Az esemény dátuma nem lehet üres!";
    public static final String START_TIME_EMPTY = "Az esemény kezdete nem lehet üres!";
    public static final String END_TIME_EMPTY = "Az esemény vége nem lehet üres!";
    public static final String DAY_EMPTY = "A nap nem lehet üres!";

    public static final String SEMESTER_NAME_EMPTY = "A szemeszter nem lehet üres!";
    public static final String SEMESTER_START_DATE_EMPTY = "A félév kezdetének dátuma nem lehet üres!";
    public static final String SEMESTER_END_DATE_EMPTY = "A félév végének dátuma nem lehet üres!";

    public static final String SUBJECT_CODE_EMPTY = "A tantárgy kódja nem lehet üres!";
    public static final String SUBJECT_NAME_EMPTY = "A tantárgy neve nem lehet üres!";

    public static final String NAME_EMPTY = "A név nem lehet üres!";
    public static final String PASSWORD_EMPTY = "A jelszó nem lehet üres!";
    public static final String EMAIL_EMPTY = "Az e-mail cím nem lehet üres!";

    public static final String CLASSROOM_EMPTY = "A tantermet meg kell adni!";
    public static final String CLASSROOM_NAME_EMPTY = "A tanterem neve nem lehet üres!";
    public static final String CLASSROOM_HAS_PC_EMPTY = "A PC nem lehet üres!";
    public static final String CLASSROOM_HAS_PROJECTOR_EMPTY = "A projektor neve nem lehet üres!";
    public static final String CLASSROOM_CHAIRS_EMPTY = "A székek száma nem lehet üres!";
    public static final String CLASSROOM_BUILDING_EMPTY = "Az épület neve nem lehet üres!";

    public static final String BUILDING_NAME_EMPTY = "Az épület neve nem lehet üres!";

    public static final String USERNAME_SIZE = "A felhasználónév nem megfelelő hosszúságú (5-30)!";
    public static final String USER_NOT_EXISTS = "Nem létezik ezzel a felhasználónévvel rendelkező felhasználó!";

    public static final String BUILDING_NOT_EXISTS = "Nem létezik ilyen nevű épület!";
    public static final String BUILDING_NAME_SIZE = "Az épület neve nem megfelelő hosszúságú (3-30)!";
    public static final String BUILDING_ALREDY_EXISTS = "Ezzel a névvel már létezik épület!";

    public static final String ROOM_NOT_EXISTS = "Ilyen nevű terem nem létezik!";
    public static final String ROOM_NAME_SIZE = "A terem neve nem megfelelő hosszúságú (3-30)!";

    public static final String SEMESTER_NAME_SIZE = "A szemeszter neve nem megfelelő hosszúságú (11)!";
    public static final String SEMESTER_NOT_EXISTS = "Ilyen szemeszter nem létezik!";
    public static final String SEMESTER_ALREDY_EXISTS = "Ezzel a névvel már létezik szemeszter!";
    public static final String SEMESTER_START_DATE_INVALID = "A szemeszter elejének dátuma nem megfelelő formátumú!";
    public static final String SEMESTER_END_DATE_INVALID = "A szemeszter végének dátuma nem megfelelő formátumú!";
    public static final String SEMESTER_END_DATE_BEFORE_START_DATE = "A szemeszter végének dátuma nem lehet korábbi, mint a kezdeti dátum!";

    public static final String CLASSROOM_ALREDY_EXISTS = "Ez a tanterem már létezik!";
    public static final String CLASSROOM_NOT_EXISTS = "Ilyen nevű tanterem nem létezik!";
    public static final String CLASSROOM_NAME_SIZE = "A tanterem neve nem megfelelő hosszúságú (3-30)!";
    public static final String CLASSROOM_CHAIRS_VALUE = "A székek száma nem nagyobb mint nulla!";

    public static final String EVENT_NAME_SIZE = "Az esemény neve nem megfelelő hosszúságú (3-30)!";

    public static final String SUBJECT_NOT_EXISTS = "Nem létezik ilyen kóddal rendelkező tantárgy!";
    public static final String SUBJECT_ALREDY_EXISTS = "Ezzel a kóddal már létezik tantárgy!";
    public static final String SUBJECT_NAME_SIZE = "A tantárgy neve nem megfelelő hosszúságú (3-30)!";
    public static final String SUBJECT_CODE_SIZE = "A tantárgy kódja nem megfelelő hosszúságú (4-10)!";

    public static final String DATE_INVALID_FORMAT = "A dátum nem megfelelő formátumú (helyesen: ÉÉÉÉ-HH-NN)!";
    public static final String TIME_INVALID_FORMAT = "Az idő nem megfelelő formátumú (helyesen: ÓÓ:PP)!";
    public static final String SEMESTER_INVALID_FORMAT = "A szemeszter neve nem megfelelő formátumú (helyesen például: 2017-2018-2)!";
    public static final String EMAIL_INVALID_FORMAT = "Az émail cím nem megfelelő formátumú!";
    public static final String DAY_INVALID_FORMAT = "A nap nem megfelelő formátumú!";

    public static final String PASSWORD_SPACE = "A jelszó nem megfelelő hosszúságú (5-30)!";
    public static final String PASSWORD_SIZE = "A jelszó nem megfelelő hosszúságú (5-50)!";
    public static final String EMAIL_SIZE = "A e-mail cím nem megfelelő hosszúságú (5-50)!";
    public static final String USER_NAME_SIZE = "A név nem megfelelő hosszúságú (5-30)!";
    public static final String USERNAME_SPACE = "A felhasználónév nem tartalmazhat szóközt!";
    public static final String USERNAME_ALREDY_EXISTS = "Ez a felhasználónév már foglalt!";
    public static final String EMAIL_ALREDY_EXISTS = "Ez az e-mail cím már használatban van!";

    public static final String RESERVATION_NOT_EXISTS = "Ilyen foglalás nem létezik!";
    public static final String END_DATE_BEFORE_START_DATE = "A befejezés időpontja nem előzheti meg a kezdés időpontját!";
    
    /*Üzenetek*/
    public static final String MESSAGE_SENDER_EMPTY = "A küldő felhasználóneve nem lehet üres!";
    public static final String MESSAGE_RECIPIENT_EMPTY = "A címzett felhasználóneve nem lehet üres!";
    public static final String MESSAGE_MESSAGE_EMPTY = "A küldött üzenet tartalma nem lehet üres!";
    public static final String MESSAGE_SENDER_SIZE = "A küldő felhasználóneve legalább (5) és legfeljebb (30) karakter hosszú lehet!";
    public static final String MESSAGE_RECIPIENT_SIZE = "A címzett felhasználóneve legalább (5) és legfeljebb (30) karakter hosszú lehet!";
    public static final String MESSAGE_MESSAGE_SIZE = "A küldött üzenet legalább (1) és legfeljebb (255) karakter hosszú lehet!";
}
