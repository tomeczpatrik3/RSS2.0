package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassReservationNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.EventReservationNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.repository.reservation.ClassReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.impl.reservation.ClassReservationServiceImpl;
import RoomReservationSystem.service.reservation.ReservationDateService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
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
 * A tanórákra vonatkozó foglalásokhoz tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassReservationServiceImplTest {

    @InjectMocks
    ClassReservationServiceImpl service;

    @Mock
    ClassReservationRepository repository;

    @Mock
    ReservationDateService reservationDateService;

    @Mock
    UserService userService;

    @Mock
    ClassroomService classroomService;

    @Mock
    StatusService statusService;
    
    @Mock
    SubjectService subjectService;
    
    @Mock
    SemesterService semesterService;

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
    
    private static final Subject TEST_SUBJECT_1 = new Subject("Teszt tantárgy 1", "test001", Collections.EMPTY_LIST, 1);
    
    private static final Subject TEST_SUBJECT_2 = new Subject("Teszt tantárgy 2", "test002", Collections.EMPTY_LIST, 2);
    
    private static final Semester TEST_SEMESTER_1 = new Semester(
            "2011-2012/1",
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(),
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            Collections.EMPTY_LIST,
            1
    );
    
    private static final Semester TEST_SEMESTER_2 = new Semester(
            "2011-2012/2",
            new GregorianCalendar(2012, Calendar.FEBRUARY, 3).getTime(),
            new GregorianCalendar(2012, Calendar.SEPTEMBER, 2).getTime(),
            Collections.EMPTY_LIST,
            2
    );

    private static final ClassReservation TEST_RESERVATION_1 = new ClassReservation(
            TEST_USER_1,
            TEST_SUBJECT_1,
            TEST_CLASS_1,
            TEST_STATUS_1,
            TEST_SEMESTER_1,
            Collections.EMPTY_LIST,
            "Ez egy teszt"
    );

    private static final ClassReservation TEST_RESERVATION_2 = new ClassReservation(
            TEST_USER_2,
            TEST_SUBJECT_2,
            TEST_CLASS_2,
            TEST_STATUS_2,
            TEST_SEMESTER_2,
            Collections.EMPTY_LIST,
            "Ez egy teszt"
    );

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindById() throws ClassReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        ClassReservation found = service.findById(TEST_RESERVATION_1.getId());
        assertEquals(TEST_RESERVATION_1, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező tanóra foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassReservationNotExistsException.class)
    public void testFindByIdException() throws ClassReservationNotExistsException {
        service.findById(1234);
    }

    /**
     * A foglalások lekérdezésének tesztelését megvalósító függvény
     */
    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_RESERVATION_1, TEST_RESERVATION_2));
        List<ClassReservation> found = service.getAll();
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
        List<ClassReservation> found = service.findByUsername(TEST_RESERVATION_1.getUser().getUsername());
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
        List<ClassReservation> found = service.findByStatus(TEST_STATUS_1.getName());
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
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testSetStatus() throws StatusNotExistsException, ClassReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        Mockito.when(statusService.findByName(TEST_STATUS_2.getName())).thenReturn(TEST_STATUS_2);
        Mockito.when(repository.save(TEST_RESERVATION_1)).thenReturn(TEST_RESERVATION_1);
        ClassReservation changed = service.setStatus(TEST_RESERVATION_1.getId(), TEST_STATUS_2.getName());
        assertNotNull(changed);
        assertEquals(changed.getStatus(), TEST_STATUS_2);
    }

    /**
     * A státusz módosításának nem létező tanóra foglalás kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassReservationNotExistsException.class)
    public void testSetStatusExceptionOne() throws StatusNotExistsException, ClassReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(null);
        service.setStatus(TEST_RESERVATION_1.getId(), TEST_STATUS_2.getName());
    }

    /**
     * A státusz módosításának nem létező státusz kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = StatusNotExistsException.class)
    public void testSetStatusExceptionTwo() throws StatusNotExistsException, ClassReservationNotExistsException {
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
        List<ClassReservation> found = service.findByBuildingAndClassroom(
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
     * A tárgykód alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindBySubjectCode() throws SubjectNotExistsException {
        Mockito.when(repository.findBySubject(TEST_RESERVATION_1.getSubject())).thenReturn(Arrays.asList(TEST_RESERVATION_1));
        Mockito.when(subjectService.findByCode(TEST_RESERVATION_1.getSubject().getCode())).thenReturn(TEST_RESERVATION_1.getSubject());
        List<ClassReservation> found = service.findBySubjectCode(TEST_RESERVATION_1.getSubject().getCode());
        assertEquals(TEST_RESERVATION_1, found.get(0));
        
        Mockito.when(repository.findBySubject(TEST_RESERVATION_2.getSubject())).thenReturn(Arrays.asList(TEST_RESERVATION_2));
        Mockito.when(subjectService.findByCode(TEST_RESERVATION_2.getSubject().getCode())).thenReturn(TEST_RESERVATION_2.getSubject());
        found = service.findBySubjectCode(TEST_RESERVATION_2.getSubject().getCode());
        assertEquals(TEST_RESERVATION_2, found.get(0));
    }

    /**
     * A tárgykód alapján történő keresés nem létező tantárgy kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testFindBySubjectCodeException() throws SubjectNotExistsException {
        Mockito.when(subjectService.findByCode(TEST_RESERVATION_1.getSubject().getCode())).thenThrow(new SubjectNotExistsException("EX"));
        service.findBySubjectCode(TEST_RESERVATION_1.getSubject().getCode());
    }
    
    /**
     * A szemeszter alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindBySemester() throws SemesterNotExistsException {
        Mockito.when(repository.findBySemester(TEST_RESERVATION_1.getSemester())).thenReturn(Arrays.asList(TEST_RESERVATION_1));
        Mockito.when(semesterService.findByName(TEST_RESERVATION_1.getSemester().getName())).thenReturn(TEST_RESERVATION_1.getSemester());
        List<ClassReservation> found = service.findBySemester(TEST_RESERVATION_1.getSemester().getName());
        assertEquals(TEST_RESERVATION_1, found.get(0));
        
        Mockito.when(repository.findBySemester(TEST_RESERVATION_2.getSemester())).thenReturn(Arrays.asList(TEST_RESERVATION_2));
        Mockito.when(semesterService.findByName(TEST_RESERVATION_2.getSemester().getName())).thenReturn(TEST_RESERVATION_2.getSemester());
        found = service.findBySemester(TEST_RESERVATION_2.getSemester().getName());
        assertEquals(TEST_RESERVATION_2, found.get(0));
    }

    /**
     * A szemeszter alapján történő keresés nem létező szemeszter kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindBySemesterException() throws SemesterNotExistsException {
        Mockito.when(semesterService.findByName(TEST_RESERVATION_1.getSemester().getName())).thenThrow(new SemesterNotExistsException("EX"));
        service.findBySemester(TEST_RESERVATION_1.getSemester().getName());
    }

    /**
     * Az azonosító alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteById() throws ClassReservationNotExistsException {
        Mockito.when(repository.findById(TEST_RESERVATION_1.getId())).thenReturn(TEST_RESERVATION_1);
        Mockito.doNothing().when(repository).deleteById(TEST_RESERVATION_1.getId());
        service.deleteById(TEST_RESERVATION_1.getId());
    }

    /**
     * Az azonosító alapján történő törlés nem létező tanóra foglalás kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws ClassReservationNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassReservationNotExistsException.class)
    public void testDeleteByIdException() throws ClassReservationNotExistsException {
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
     * A felhasználónév alapján történő törlés nem létező tanóra foglalás
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
}
