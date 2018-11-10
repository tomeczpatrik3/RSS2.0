package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.SemesterNotOpenedException;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.repository.SemesterRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * A szemeszterekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class SemesterServiceImplTest {

    @InjectMocks
    SemesterServiceImpl service;

    @Mock
    SemesterRepository repository;

    private final Semester TEST_SEMESTER_1 = new Semester(
            "2011-2012/1",
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(),
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            false,
            Collections.EMPTY_LIST,
            999
    );

    private final Semester TEST_SEMESTER_2 = new Semester(
            "2011-2012/2",
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(),
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            true,
            Collections.EMPTY_LIST,
            999
    );

    /**
     * A szemeszterek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER_1));
        List<Semester> found = service.getAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER_1, found.get(0));
    }

    /**
     * A nyitott szemeszterek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetOpened() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER_1, TEST_SEMESTER_2));
        List<Semester> found = service.getOpened();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER_2, found.get(0));
    }

    /**
     * A mentés tesztelésére szolgáló függvény
     *
     * @throws SemesterAlredyExistsException A lehetséges kivétel, ha a
     * szemeszter már létezik
     */
    @Test
    public void testSave() throws SemesterAlredyExistsException {
        Mockito.when(repository.save(TEST_SEMESTER_1)).thenReturn(TEST_SEMESTER_1);
        Semester found = service.save(TEST_SEMESTER_1);
        assertNotNull(found);
        assertEquals(TEST_SEMESTER_1, found);
    }

    /**
     * A mentés már létező szemeszter kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws SemesterAlredyExistsException A lehetséges kivétel, ha a
     * szemeszter már létezik
     */
    @Test(expected = SemesterAlredyExistsException.class)
    public void testSaveException() throws SemesterAlredyExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER_1.getName())).thenReturn(TEST_SEMESTER_1);
        service.save(TEST_SEMESTER_1);
    }

    /**
     * A frissítés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test
    public void testUpdate() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER_1.getId())).thenReturn(TEST_SEMESTER_1);
        Mockito.when(repository.save(TEST_SEMESTER_1)).thenReturn(TEST_SEMESTER_1);
        Semester updated = service.update(TEST_SEMESTER_1.getId(), TEST_SEMESTER_1);
        assertEquals(TEST_SEMESTER_1, updated);
    }

    /**
     * A frissítés nem létező szemeszter kivétel kiváltásának tesztelésére
     * szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testUpdateException() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER_1.getId())).thenReturn(null);
        service.update(TEST_SEMESTER_1.getId(), TEST_SEMESTER_1);
    }

    /**
     * A szemeszterek neveinek a lekérdezését tesztelő függvény
     */
    @Test
    public void testGetOpenedNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER_1, TEST_SEMESTER_2));
        List<String> found = service.getOpenedNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER_2.getName(), found.get(0));
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test
    public void testFindByName() throws SemesterNotExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER_1.getName())).thenReturn(TEST_SEMESTER_1);
        Semester found = service.findByName(TEST_SEMESTER_1.getName());
        assertNotNull(found);
        assertEquals(TEST_SEMESTER_1, found);
    }

    /**
     * A név alapján történő keresés nem létező szemeszter kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindByNameException() throws SemesterNotExistsException {
        service.findByName("EXCEPTION");
    }

    /**
     * A név alapján történő nyitott szemeszter keresésést tesztelő függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     */
    @Test
    public void testFindOpenedByName() throws SemesterNotExistsException, SemesterNotOpenedException {
        Mockito.when(repository.findByName(TEST_SEMESTER_2.getName())).thenReturn(TEST_SEMESTER_2);
        Semester found = service.findOpenedByName(TEST_SEMESTER_2.getName());
        assertNotNull(found);
        assertEquals(TEST_SEMESTER_2, found);
    }

    /**
     * A név alapján történő nyitott szemeszter keresésénel nem létező
     * szemeszter kivétel kiváltásának tesztelését végző függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindOpenedByNameExceptionOne() throws SemesterNotExistsException, SemesterNotOpenedException {
        service.findOpenedByName("EXCEPTION");
    }

    /**
     * A név alapján történő nyitott szemeszter keresésénel nem nyitott
     * szemeszter kivétel kiváltásának tesztelését végző függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     */
    @Test(expected = SemesterNotOpenedException.class)
    public void testFindOpenedByNameExceptionTwo() throws SemesterNotExistsException, SemesterNotOpenedException {
        Mockito.when(repository.findByName(TEST_SEMESTER_1.getName())).thenReturn(TEST_SEMESTER_1);
        service.findOpenedByName(TEST_SEMESTER_1.getName());
    }

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test
    public void testFindById() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER_1.getId())).thenReturn(TEST_SEMESTER_1);
        Semester found = service.findById(TEST_SEMESTER_1.getId());
        assertEquals(TEST_SEMESTER_1, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindByIdException() throws SemesterNotExistsException {
        service.findById(1234);
    }

    /**
     * A név alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test
    public void testDeleteByName() throws SemesterNotExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER_1.getName())).thenReturn(TEST_SEMESTER_1);
        Mockito.doNothing().when(repository).deleteByName(TEST_SEMESTER_1.getName());
        service.deleteByName(TEST_SEMESTER_1.getName());
    }

    /**
     * A név alapján történő törlés nem létező szemeszter kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testDeleteByNameException() throws SemesterNotExistsException {
        service.deleteByName("EXCEPTION");
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_SEMESTER_1.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_SEMESTER_1.getId());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsById(TEST_SEMESTER_1.getId())).thenReturn(false);
        exists = service.existsById(TEST_SEMESTER_1.getId());
        Assert.assertFalse(exists);
    }

    /**
     * A név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_SEMESTER_1.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_SEMESTER_1.getName());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_SEMESTER_1.getName())).thenReturn(false);
        exists = service.existsByName(TEST_SEMESTER_1.getName());
        Assert.assertFalse(exists);
    }
}
