package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.EventDTO;
import RoomReservationSystem.dto.ReservationInfoDTO;
import RoomReservationSystem.enums.ReservationType;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.ClassReservation;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.EventReservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.service.ClassReservationService;
import RoomReservationSystem.service.EventReservationService;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Az eseményekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @InjectMocks
    EventServiceImpl service;

    @Mock
    EventReservationService eService;

    @Mock
    ClassReservationService cService;

    private static final User TEST_USER_1 = new User(
            "tesztJani",
            "testpw",
            "Teszt János",
            "tesztjani@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            1
    );

    private static final User TEST_USER_2 = new User(
            "tesztGabi",
            "testpw",
            "Teszt Gábor",
            "tesztgabi@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            2
    );

    private static final Building TEST_BUILDING_1 = new Building("Teszt Épület 1", Collections.EMPTY_LIST, 1);

    private static final Building TEST_BUILDING_2 = new Building("Teszt Épület 2", Collections.EMPTY_LIST, 2);

    private static final Classroom TEST_CLASS_1 = new Classroom(
            "Teszt terem 1",
            true,
            false,
            40,
            Collections.EMPTY_LIST,
            TEST_BUILDING_1
    );

    private static final Classroom TEST_CLASS_2 = new Classroom(
            "Teszt terem 2",
            true,
            true,
            100,
            Collections.EMPTY_LIST,
            TEST_BUILDING_2
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

    private static final ReservationDate TEST_DATE = new ReservationDate(
            null,
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
    );

    private static final ClassReservation TEST_CLASS_RES = new ClassReservation(
            TEST_USER_1,
            TEST_SUBJECT_1,
            TEST_CLASS_1,
            TEST_STATUS_1,
            TEST_SEMESTER_1,
            Arrays.asList(TEST_DATE),
            "Ez egy teszt"
    );

    private static final EventReservation TEST_EVENT_RES = new EventReservation(
            TEST_USER_2,
            TEST_CLASS_2,
            TEST_STATUS_1,
            Arrays.asList(TEST_DATE),
            "Teszt esemény foglalás",
            "Ez egy teszt"
    );

    private static final EventDTO TEST_EVENT_1 = new EventDTO(
            TEST_DATE.getStart().toString(),
            TEST_DATE.getEnd().toString(),
            String.format(
                    "%s - %s: %s",
                    TEST_CLASS_RES.getClassroom().getBuilding().getName(),
                    TEST_CLASS_RES.getClassroom().getName(),
                    TEST_CLASS_RES.getSubject().getName()
            ),
            new ReservationInfoDTO(
                    TEST_CLASS_RES.getId(),
                    ReservationType.CLASS,
                    TEST_CLASS_RES.getUser().getName(),
                    TEST_CLASS_RES.getClassroom().getBuilding().getName(),
                    TEST_CLASS_RES.getClassroom().getName(),
                    TEST_CLASS_RES.getNote(),
                    TEST_CLASS_RES.getSubject().getName(),
                    TEST_CLASS_RES.getSemester().getName()
            )
    );

    private static final EventDTO TEST_EVENT_2 = new EventDTO(
            TEST_DATE.getStart().toString(),
            TEST_DATE.getEnd().toString(),
            String.format(
                    "%s - %s: %s",
                    TEST_EVENT_RES.getClassroom().getBuilding().getName(),
                    TEST_EVENT_RES.getClassroom().getName(),
                    TEST_EVENT_RES.getName()
            ),
            new ReservationInfoDTO(
                    TEST_EVENT_RES.getId(),
                    ReservationType.EVENT,
                    TEST_EVENT_RES.getUser().getName(),
                    TEST_EVENT_RES.getClassroom().getBuilding().getName(),
                    TEST_EVENT_RES.getClassroom().getName(),
                    TEST_EVENT_RES.getNote(),
                    TEST_EVENT_RES.getName()
            )
    );

    @Before
    public void setUp() throws StatusNotExistsException {
        Mockito.when(cService.findByStatus("ACCEPTED")).thenReturn(Arrays.asList(TEST_CLASS_RES));
        Mockito.when(eService.findByStatus("ACCEPTED")).thenReturn(Arrays.asList(TEST_EVENT_RES));
    }

    /**
     * Az összes esemény lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetEvent() {
        List<EventDTO> found = service.getEvents();
        assertNotNull(found);
        assertThat(found.size(), is(2));
        assertEquals(TEST_EVENT_1, found.get(0));
        assertEquals(TEST_EVENT_2, found.get(1));
    }

    /**
     * A felhasználó teljes neve alapján történő keresés tesztelését megvalósító
     * függvény
     */
    @Test
    public void testFindByUserName() {
        List<EventDTO> found = service.findByUserName(TEST_EVENT_1.getInfo().getName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_1, found.get(0));
    }

    /**
     * Az épület neve alapján történő keresés tesztelését megvalósító függvény
     */
    @Test
    public void testFindByBuildingName() {
        List<EventDTO> found = service.findByBuildingName(TEST_EVENT_1.getInfo().getBuilding());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_1, found.get(0));
    }

    /**
     * A tanterem és épület neve alapján történő keresés tesztelését megvalósító
     * függvény
     */
    @Test
    public void testFindByClassroomNameAndBuilding() {
        List<EventDTO> found = service.findByClassroomNameAndBuilding(
                TEST_EVENT_1.getInfo().getClassroom(),
                TEST_EVENT_1.getInfo().getBuilding()
        );
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_1, found.get(0));
    }

    /**
     * Az esemény neve alapján történő keresés tesztelését megvalósító függvény
     */
    @Test
    public void testFindByEventName() {
        List<EventDTO> found = service.findByEventName(TEST_EVENT_2.getInfo().getEventName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_2, found.get(0));
    }

    /**
     * A tantárgy neve alapján történő keresés tesztelését megvalósító függvény
     */
    @Test
    public void testFindBySubjectName() {
        List<EventDTO> found = service.findBySubjectName(TEST_EVENT_1.getInfo().getSubject());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_1, found.get(0));
    }

    /**
     * A szemeszter neve alapján történő keresés tesztelését megvalósító
     * függvény
     */
    @Test
    public void testFindBySemesterName() {
        List<EventDTO> found = service.findBySemesterName(TEST_EVENT_1.getInfo().getSemester());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_EVENT_1, found.get(0));
    }
}
