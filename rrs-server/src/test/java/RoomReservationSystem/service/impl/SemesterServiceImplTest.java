package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
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
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class SemesterServiceImplTest {

    @InjectMocks
    SemesterServiceImpl service;

    @Mock
    SemesterRepository repository;

    private final Semester TEST_SEMESTER = new Semester(
            "2011-2012/1",
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(),
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            Collections.EMPTY_LIST,
            999
    );

    /**
     * A szemeszterek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER));
        List<Semester> found = service.getAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER, found.get(0));
    }

    /**
     * A mentés tesztelésére szolgáló függvény
     *
     * @throws SemesterAlredyExistsException A lehetséges kivétel
     */
    @Test
    public void testSave() throws SemesterAlredyExistsException {
        Mockito.when(repository.save(TEST_SEMESTER)).thenReturn(TEST_SEMESTER);
        Semester found = service.save(TEST_SEMESTER);
        assertNotNull(found);
        assertEquals(TEST_SEMESTER, found);
    }

    /**
     * A mentés már létező szemeszter kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws SemesterAlredyExistsException A lehetséges kivétel
     */
    @Test(expected = SemesterAlredyExistsException.class)
    public void testSaveException() throws SemesterAlredyExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER.getName())).thenReturn(TEST_SEMESTER);
        service.save(TEST_SEMESTER);
    }
    
    /**
     * A frissítés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test
    public void testUpdate() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER.getId())).thenReturn(TEST_SEMESTER);
        Mockito.when(repository.save(TEST_SEMESTER)).thenReturn(TEST_SEMESTER);
        Semester updated = service.update(TEST_SEMESTER.getId(), TEST_SEMESTER);
        assertEquals(TEST_SEMESTER, updated);
    }
    
    /**
     * A frissítés nem létező szemeszter kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */    
    @Test(expected = SemesterNotExistsException.class)
    public void testUpdateException() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER.getId())).thenReturn(null);
        service.update(TEST_SEMESTER.getId(), TEST_SEMESTER);
    }

    /**
     * A szemeszterek neveinek a lekérdezését tesztelő függvény
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER.getName(), found.get(0));
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByName() throws SemesterNotExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER.getName())).thenReturn(TEST_SEMESTER);
        Semester found = service.findByName(TEST_SEMESTER.getName());
        assertNotNull(found);
        assertEquals(TEST_SEMESTER, found);
    }

    /**
     * A név alapján történő keresés nem létező szemeszter kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindByNameException() throws SemesterNotExistsException {
        service.findByName("EXCEPTION");
    }
    
     /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindById() throws SemesterNotExistsException {
        Mockito.when(repository.findById(TEST_SEMESTER.getId())).thenReturn(TEST_SEMESTER);
        Semester found = service.findById(TEST_SEMESTER.getId());
        assertEquals(TEST_SEMESTER, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test(expected = SemesterNotExistsException.class)
    public void testFindByIdException() throws SemesterNotExistsException {
        service.findById(1234);
    }   

    /**
     * A név alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByName() throws SemesterNotExistsException {
        Mockito.when(repository.findByName(TEST_SEMESTER.getName())).thenReturn(TEST_SEMESTER);
        Mockito.doNothing().when(repository).deleteByName(TEST_SEMESTER.getName());
        service.deleteByName(TEST_SEMESTER.getName());
    }

    /**
     * A név alapján történő törlés nem létező szemeszter kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws SemesterNotExistsException A lehetséges kivétel
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
        Mockito.when(repository.existsById(TEST_SEMESTER.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_SEMESTER.getId());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsById(TEST_SEMESTER.getId())).thenReturn(false);
        exists = service.existsById(TEST_SEMESTER.getId());
        Assert.assertFalse(exists);
    }

    /**
     * A név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_SEMESTER.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_SEMESTER.getName());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_SEMESTER.getName())).thenReturn(false);
        exists = service.existsByName(TEST_SEMESTER.getName());
        Assert.assertFalse(exists);
    }
}
