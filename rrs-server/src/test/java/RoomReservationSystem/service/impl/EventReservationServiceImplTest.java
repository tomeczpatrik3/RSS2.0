package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.EventReservationNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.repository.reservation.EventReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.impl.reservation.EventReservationServiceImpl;
import RoomReservationSystem.service.reservation.ReservationDateService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class EventReservationServiceImplTest {

    @InjectMocks
    EventReservationServiceImpl service;

    @Mock
    EventReservationRepository repository;

    @Mock
    ReservationDateService reservationDateService;

    @Mock
    UserService userService;

    @Mock
    ClassroomService classroomService;

    @Mock
    StatusService statusService;

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

    private static final Status TEST_STATUS_2 = new Status("DECLINED", "Elutasított", Collections.EMPTY_LIST, 2);

    private static final EventReservation TEST_RESERVATION_1 = new EventReservation(
            TEST_USER_1,
            TEST_CLASS_1,
            TEST_STATUS_1,
            Collections.EMPTY_LIST,
            "Teszt esemény foglalás 1",
            "Ez egy teszt"
    );

    private static final EventReservation TEST_RESERVATION_2 = new EventReservation(
            TEST_USER_2,
            TEST_CLASS_2,
            TEST_STATUS_2,
            Collections.EMPTY_LIST,
            "Teszt esemény foglalás 2",
            "Ez egy teszt"
    );
    
    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindById() throws EventReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        EventReservation found = service.findById(TEST_RESERVATION_1.getId());
        assertEquals(TEST_RESERVATION_1, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező esemény foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = EventReservationNotExistsException.class)
    public void testFindByIdException() throws EventReservationNotExistsException {
        service.findById(1234);
    }

    /**
     * A foglalások lekérdezésének tesztelését megvalósító függvény
     */
    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_RESERVATION_1, TEST_RESERVATION_2));
        List<EventReservation> found = service.getAll();
        assertNotNull(found);
        assertThat(found.size(), is(2));
        assertEquals(TEST_RESERVATION_1, found.get(0));
        assertEquals(TEST_RESERVATION_2, found.get(1));
    }

    /**
     * A felhasználónév alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByUsername() throws UserNotExistsException {
        Mockito.when(userService.findByUsername(TEST_RESERVATION_1.getUser().getUsername())).thenReturn(TEST_RESERVATION_1.getUser());
        Mockito.when(repository.findByUser(TEST_RESERVATION_1.getUser())).thenReturn(Arrays.asList(TEST_RESERVATION_1));
        List<EventReservation> found = service.findByUsername(TEST_RESERVATION_1.getUser().getUsername());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_RESERVATION_1, found.get(0));
    }

    /**
     * A felhasználónév alapján történő keresés nem létező felhasználó kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindByUsernameException() throws UserNotExistsException {
        Mockito.when(userService.findByUsername(TEST_RESERVATION_1.getUser().getUsername())).thenThrow(new UserNotExistsException("EX"));
        service.findByUsername(TEST_RESERVATION_1.getUser().getUsername());
    }

    /**
     * A státusz alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByStatus() throws StatusNotExistsException {
        Mockito.when(statusService.findByName(TEST_STATUS_1.getName())).thenReturn(TEST_STATUS_1);
        Mockito.when(repository.findByStatus(TEST_STATUS_1)).thenReturn(Arrays.asList(TEST_RESERVATION_1));
        List<EventReservation> found = service.findByStatus(TEST_STATUS_1.getName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_RESERVATION_1, found.get(0));
    }

    /**
     * A státusz alapján történő keresés nem létező státusz kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test(expected = StatusNotExistsException.class)
    public void testFindByStatusException() throws StatusNotExistsException {
        Mockito.when(statusService.findByName(TEST_STATUS_1.getName())).thenThrow(new StatusNotExistsException("EX"));
        service.findByStatus(TEST_STATUS_1.getName());
    }

    /**
     * A státusz módosításának tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testSetStatus() throws StatusNotExistsException, EventReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        Mockito.when(statusService.findByName(TEST_STATUS_2.getName())).thenReturn(TEST_STATUS_2);
        Mockito.when(repository.save(TEST_RESERVATION_1)).thenReturn(TEST_RESERVATION_1);
        EventReservation changed = service.setStatus(TEST_RESERVATION_1.getId(), TEST_STATUS_2.getName());
        assertNotNull(changed);
        assertEquals(TEST_RESERVATION_1.getName(), changed.getName());
        assertEquals(changed.getStatus(), TEST_STATUS_2);
    }

    /**
     * A státusz módosításának nem létező esemény foglalás kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = EventReservationNotExistsException.class)
    public void testSetStatusExceptionOne() throws StatusNotExistsException, EventReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(null);
        service.setStatus(TEST_RESERVATION_1.getId(), TEST_STATUS_2.getName());
    }

    /**
     * A státusz módosításának nem létező státusz kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = StatusNotExistsException.class)
    public void testSetStatusExceptionTwo() throws StatusNotExistsException, EventReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(null);
        Mockito.when(statusService.findByName(TEST_STATUS_2.getName())).thenThrow(new StatusNotExistsException("EX"));
        service.setStatus(TEST_RESERVATION_1.getId(), TEST_STATUS_2.getName());
    }

    /**
     * Az épület és tanterem alapján történő keresés tesztelésére szolgáló
     * függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByBuildingAndClassroom() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenReturn(TEST_RESERVATION_1.getClassroom());
        Mockito.when(repository.findByClassroom(TEST_RESERVATION_1.getClassroom())).thenReturn(Arrays.asList(TEST_RESERVATION_1));
        List<EventReservation> found = service.findByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_RESERVATION_1, found.get(0));
    }

    /**
     * Az épület és tanterem alapján történő keresés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByBuildingAndClassroomExceptionOne() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenThrow(new BuildingNotExistsException("EX"));
        service.findByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
    }

    /**
     * Az épület és tanterem alapján történő keresés nem létező tanterem kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassroomNotExistsException.class)
    public void testFindByBuildingAndClassroomExceptionTwo() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenThrow(new ClassroomNotExistsException(1));
        service.findByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByName() throws EventReservationNotExistsException {
        Mockito.when(repository.findByName(TEST_RESERVATION_1.getName())).thenReturn(TEST_RESERVATION_1);
        EventReservation found = service.findByName(TEST_RESERVATION_1.getName());
        assertEquals(TEST_RESERVATION_1, found);
    }

    /**
     * A név alapján történő keresés nem létező esemény foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = EventReservationNotExistsException.class)
    public void testFindByNameException() throws EventReservationNotExistsException {
        service.findByName("EXCEPTION");
    }

    /**
     * Az azonosító alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteById() throws EventReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        Mockito.doNothing().when(repository).deleteById(TEST_RESERVATION_1.getId());
        service.deleteById(TEST_RESERVATION_1.getId());
    }

    /**
     * Az azonosító alapján történő törlés nem létező esemény foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = EventReservationNotExistsException.class)
    public void testDeleteByIdException() throws EventReservationNotExistsException {
        service.deleteById(1);
    }

    /**
     * A felhasználónév alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByUsername() throws UserNotExistsException {
        Mockito.when(userService.findByUsername(TEST_USER_1.getUsername())).thenReturn(TEST_USER_1);
        Mockito.doNothing().when(repository).deleteByUser(TEST_USER_1);
        service.deleteByUsername(TEST_USER_1.getUsername());
    }

    /**
     * A felhasználónév alapján történő törlés nem létező esemény foglalás
     * kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testDeleteByUsernameException() throws UserNotExistsException {
        Mockito.when(userService.findByUsername(TEST_USER_1.getUsername())).thenThrow(new UserNotExistsException("EX"));
        service.deleteByUsername(TEST_USER_1.getUsername());
    }

    /**
     * Az épület és tanterem alapján történő törlés tesztelésére szolgáló
     * függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByBuildingAndClassroom() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenReturn(TEST_RESERVATION_1.getClassroom());

        Mockito.doNothing().when(repository).deleteByClassroom(TEST_RESERVATION_1.getClassroom());

        service.deleteByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
    }

    /**
     * Az épület és tanterem alapján történő törlés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testDeleteByBuildingAndClassroomExceptionOne() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenThrow(new BuildingNotExistsException("EX"));

        service.deleteByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
    }

    /**
     * Az épület és tanterem alapján történő törlés nem létező tanterem kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassroomNotExistsException A lehetséges kivétel
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassroomNotExistsException.class)
    public void testDeleteByBuildingAndClassroomExceptionTwo() throws ClassroomNotExistsException, BuildingNotExistsException {
        Mockito.when(classroomService.findByNameAndBuildingName(
                TEST_RESERVATION_1.getClassroom().getBuilding().getName(),
                TEST_RESERVATION_1.getClassroom().getName())
        )
                .thenThrow(new ClassroomNotExistsException(1));

        service.deleteByBuildingAndClassroom(
                TEST_RESERVATION_1.getClassroom().getName(),
                TEST_RESERVATION_1.getClassroom().getBuilding().getName()
        );
    }

    /**
     * A státusz alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByStatus() throws StatusNotExistsException {
        Mockito.when(statusService.findByName(TEST_STATUS_1.getName())).thenReturn(TEST_STATUS_1);
        Mockito.doNothing().when(repository).deleteByStatus(TEST_STATUS_1);
        service.deleteByStatus(TEST_STATUS_1.getName());
    }

    /**
     * A státusz alapján történő törlés nem létező státusz kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test(expected = StatusNotExistsException.class)
    public void testDeleteByStatusException() throws StatusNotExistsException {
        Mockito.when(statusService.findByName(TEST_STATUS_1.getName())).thenThrow(new StatusNotExistsException("EX"));
        service.deleteByStatus(TEST_STATUS_1.getName());
    }

    /**
     * A név alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByName() throws EventReservationNotExistsException {
        Mockito.when(repository.findByName(TEST_RESERVATION_1.getName())).thenReturn(TEST_RESERVATION_1);
        Mockito.doNothing().when(repository).deleteByName(TEST_RESERVATION_1.getName());
        service.deleteByName(TEST_RESERVATION_1.getName());
    }

    /**
     * A név alapján történő törlés nem létező esemény foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws EventReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = EventReservationNotExistsException.class)
    public void testDeleteByNameException() throws EventReservationNotExistsException {
        Mockito.when(repository.findByName(TEST_RESERVATION_1.getName())).thenReturn(null);
        service.deleteByName(TEST_RESERVATION_1.getName());
    }
    
    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_RESERVATION_1.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_RESERVATION_1.getId());
        assertTrue(exists);

        Mockito.when(repository.existsById(TEST_RESERVATION_1.getId())).thenReturn(false);
        exists = service.existsById(TEST_RESERVATION_1.getId());
        assertFalse(exists);
    }

    /**
     * A név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_RESERVATION_1.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_RESERVATION_1.getName());
        assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_RESERVATION_1.getName())).thenReturn(false);
        exists = service.existsByName(TEST_RESERVATION_1.getName());
        assertFalse(exists);
    }
    
    /**
     * Az események neveinek lekérdezését tesztelő függvény
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_RESERVATION_1, TEST_RESERVATION_2));
        List<String> names = service.getNames();
        assertNotNull(names);
        assertThat(names.size(), is(2));
        assertEquals(TEST_RESERVATION_1.getName(), names.get(0));
        assertEquals(TEST_RESERVATION_2.getName(), names.get(1));
    }

}
