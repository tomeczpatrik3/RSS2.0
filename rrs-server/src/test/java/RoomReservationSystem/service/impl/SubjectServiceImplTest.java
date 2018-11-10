package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.repository.SubjectRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * A tantárgyakhoz tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceImplTest {

    @InjectMocks
    SubjectServiceImpl service;

    @Mock
    SubjectRepository repository;

    private static final Subject TEST_SUBJECT_1 = new Subject("TEST_SUBJECT", "TEST001", Collections.EMPTY_LIST, 1);
    private static final Subject TEST_SUBJECT_2 = new Subject("TEST_SUBJECT", "TEST002", Collections.EMPTY_LIST, 2);

    /**
     * A mentés tesztelésére szolgáló függvény
     *
     * @throws SubjectAlredyExistsException A lehetséges kivétel, ha a tantárgy
     * már létezik
     */
    @Test
    public void testSave() throws SubjectAlredyExistsException {
        Mockito.when(repository.save(TEST_SUBJECT_1)).thenReturn(TEST_SUBJECT_1);
        Subject found = service.save(TEST_SUBJECT_1);
        assertNotNull(found);
        assertEquals(TEST_SUBJECT_1, found);
    }

    /**
     * A mentés már létező tantárgy kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws SubjectAlredyExistsException A lehetséges kivétel, ha a tantárgy
     * már létezik
     */
    @Test(expected = SubjectAlredyExistsException.class)
    public void testSaveException() throws SubjectAlredyExistsException {
        Mockito.when(repository.findByCode(TEST_SUBJECT_1.getCode())).thenReturn(TEST_SUBJECT_1);
        service.save(TEST_SUBJECT_1);
    }

    /**
     * A frissítés tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test
    public void testUpdate() throws SubjectNotExistsException {
        Mockito.when(repository.findById(TEST_SUBJECT_1.getId())).thenReturn(TEST_SUBJECT_1);
        Mockito.when(repository.save(TEST_SUBJECT_1)).thenReturn(TEST_SUBJECT_1);
        Subject updated = service.update(TEST_SUBJECT_1.getId(), TEST_SUBJECT_1);
        assertEquals(TEST_SUBJECT_1, updated);
    }

    /**
     * A frissítés nem létező tantárgy kivétel kiváltásának tesztelésére
     * szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testUpdateException() throws SubjectNotExistsException {
        Mockito.when(repository.findById(TEST_SUBJECT_1.getId())).thenReturn(null);
        service.update(TEST_SUBJECT_1.getId(), TEST_SUBJECT_1);
    }

    /**
     * A kód alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test
    public void testDeleteByCode() throws SubjectNotExistsException {
        Mockito.when(repository.findByCode(TEST_SUBJECT_1.getCode())).thenReturn(TEST_SUBJECT_1);
        Mockito.doNothing().when(repository).deleteByCode(TEST_SUBJECT_1.getCode());
        service.deleteByCode(TEST_SUBJECT_1.getCode());
    }

    /**
     * A kód alapján történő törlés nem létező tantárgy kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testDeleteByCodeException() throws SubjectNotExistsException {
        service.deleteByCode("EXCEPTION");
    }

    /**
     * A tantárgyak lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<Subject> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(2));
    }

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test
    public void testFindById() throws SubjectNotExistsException {
        Mockito.when(repository.findById(TEST_SUBJECT_1.getId())).thenReturn(TEST_SUBJECT_1);
        Subject found = service.findById(TEST_SUBJECT_1.getId());
        assertEquals(TEST_SUBJECT_1, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező tantárgy kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testFindByIdException() throws SubjectNotExistsException {
        service.findById(1234);
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     */
    @Test
    public void testFindByName() {
        Mockito.when(repository.findByName("TEST_SUBJECT")).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<Subject> found = service.findByName("TEST_SUBJECT");
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(repository.findByName("EMPTY")).thenReturn(Collections.EMPTY_LIST);
        found = service.findByName("EMPTY");
        assertNotNull(found);
        assertThat(found.size(), is(0));
    }

    /**
     * A tárgykód alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test
    public void testFindByCode() throws SubjectNotExistsException {
        Mockito.when(repository.findByCode(TEST_SUBJECT_1.getCode())).thenReturn(TEST_SUBJECT_1);
        Subject found = service.findByCode(TEST_SUBJECT_1.getCode());
        assertEquals(TEST_SUBJECT_1, found);
    }

    /**
     * A tárgykód alapján történő keresés nem létező tantárgy kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testFindByCodeException() throws SubjectNotExistsException {
        service.findByCode("EXCEPTION");
    }

    /**
     * A tantárgyak neveinek lekérdezését tesztelő függvény
     */
    @Test
    public void testGetSubjectNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<String> found = service.getSubjectNames();
        assertNotNull(found);
        assertThat(found.size(), is(2));
    }

    /**
     * A tárgykódhoz tartozó tantárgy nevének lekérdezését tesztelő függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test
    public void testGetSubjectName() throws SubjectNotExistsException {
        Mockito.when(repository.findByCode(TEST_SUBJECT_1.getCode())).thenReturn(TEST_SUBJECT_1);
        Map<String, String> name = service.getSubjectName(TEST_SUBJECT_1.getCode());
        assertEquals(TEST_SUBJECT_1.getName(), name.get("name"));
    }

    /**
     * A tárgykódhoz tartozó tantárgy nevének nem lekérdezése nem létező
     * tantárgy kivétel kiváltását tesztelő függvény
     *
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Test(expected = SubjectNotExistsException.class)
    public void testGetSubjectNameException() throws SubjectNotExistsException {
        Mockito.when(repository.findByCode(TEST_SUBJECT_1.getCode())).thenReturn(null);
        service.getSubjectName(TEST_SUBJECT_1.getCode());
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_SUBJECT_1.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_SUBJECT_1.getId());
        assertTrue(exists);

        Mockito.when(repository.existsById(TEST_SUBJECT_1.getId())).thenReturn(false);
        exists = service.existsById(TEST_SUBJECT_1.getId());
        assertFalse(exists);
    }

    /**
     * A tárgykód alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByCode() {
        Mockito.when(repository.existsByCode(TEST_SUBJECT_1.getCode())).thenReturn(true);
        boolean exists = service.existsByCode(TEST_SUBJECT_1.getCode());
        assertTrue(exists);

        Mockito.when(repository.existsByCode(TEST_SUBJECT_1.getCode())).thenReturn(false);
        exists = service.existsByCode(TEST_SUBJECT_1.getCode());
        assertFalse(exists);
    }
}
