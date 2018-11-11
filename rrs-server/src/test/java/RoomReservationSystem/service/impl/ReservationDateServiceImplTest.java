package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.ClassReservation;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.ReservationDateRepository;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Az foglalási dátumokhoz tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationDateServiceImplTest {

    @InjectMocks
    ReservationDateServiceImpl service;

    @Mock
    ReservationDateRepository repository;

    private static final User TEST_USER_1 = new User(
            "tesztJani",
            "testpw",
            "Teszt János",
            "tesztjani@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            1
    );

    private static final Building TEST_BUILDING_1 = new Building("Teszt Épület 1", Collections.EMPTY_LIST, 1);

    private static final Classroom TEST_CLASS_1 = new Classroom(
            "Teszt terem 1",
            true,
            false,
            40,
            Collections.EMPTY_LIST,
            TEST_BUILDING_1
    );

    private static final Status TEST_STATUS_1 = new Status("ACCEPTED", "Elfogadott", Collections.EMPTY_LIST, 1);

    private static final Subject TEST_SUBJECT_1 = new Subject("Teszt tantárgy 1", "test001", Collections.EMPTY_LIST, 1);

    private static final Semester TEST_SEMESTER_1 = new Semester(
            "2011-2012/1",
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(),
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            false,
            Collections.EMPTY_LIST,
            1
    );

    private static final ClassReservation TEST_RESERVATION = new ClassReservation(
            TEST_USER_1,
            TEST_SUBJECT_1,
            TEST_CLASS_1,
            TEST_STATUS_1,
            TEST_SEMESTER_1,
            Collections.EMPTY_LIST,
            "Ez egy teszt"
    );

    private static final ReservationDate TEST_DATE = new ReservationDate(
            TEST_RESERVATION,
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
    );
    
    /**
     * A mentés tesztelésére szolgáló függvény
     */
    @Test
    public void testSave() {
        Mockito.when(repository.save(TEST_DATE)).thenReturn(TEST_DATE);
        ReservationDate found = repository.save(TEST_DATE);
        assertEquals(TEST_DATE, found);
    }
    
    /**
     * A foglalás alapján történő törlés tesztelésére szolgáló függvény
     */
    @Test
    public void testDeleteByReservation() {
        Mockito.doNothing().when(repository).deleteByReservation(TEST_RESERVATION);
        service.deleteByReservation(TEST_RESERVATION);
    }
}
