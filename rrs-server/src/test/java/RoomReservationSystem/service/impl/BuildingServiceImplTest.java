package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.BuildingAlredyExistsException;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.repository.BuildingRepository;
import java.util.Arrays;
import java.util.Collections;
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
 * Az épületekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceImplTest {

    @InjectMocks
    BuildingServiceImpl service;

    @Mock
    BuildingRepository repository;

    private final Building TEST_BUILDING = new Building("TEST_BUILDING", Collections.EMPTY_LIST, 999);

    /**
     * A név alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test
    public void testDeleteByName() throws BuildingNotExistsException {
        Mockito.when(repository.findByName("TEST_BUILDING")).thenReturn(TEST_BUILDING);
        Mockito.doNothing().when(repository).deleteByName("TEST_BUILDING");
        service.deleteByName("TEST_BUILDING");
    }

    /**
     * A név alapján történő törlés nem létező épület kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testDeleteByNameException() throws BuildingNotExistsException {
        service.deleteByName("EXCEPTION");
    }

    /**
     * A mentés tesztelésére szolgáló függvény
     *
     * @throws BuildingAlredyExistsException A lehetséges kivétel, ha az épület
     * már létezik
     */
    @Test
    public void testSave() throws BuildingAlredyExistsException {
        Mockito.when(repository.save(TEST_BUILDING)).thenReturn(TEST_BUILDING);
        Building found = service.save(TEST_BUILDING);
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * A mentés már létező épület kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws BuildingAlredyExistsException A lehetséges kivétel, ha az épület
     * már létezik
     */
    @Test(expected = BuildingAlredyExistsException.class)
    public void testSaveException() throws BuildingAlredyExistsException {
        Mockito.when(repository.findByName(TEST_BUILDING.getName())).thenReturn(TEST_BUILDING);
        service.save(TEST_BUILDING);
    }

    /**
     * A frissítés tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test
    public void testUpdate() throws BuildingNotExistsException {
        Mockito.when(repository.findById(TEST_BUILDING.getId())).thenReturn(TEST_BUILDING);
        Mockito.when(repository.save(TEST_BUILDING)).thenReturn(TEST_BUILDING);
        Building found = service.update(TEST_BUILDING.getId(), TEST_BUILDING);
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * A frissítés nem létező épület kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testUpdateException() throws BuildingNotExistsException {
        service.update(12345, TEST_BUILDING);
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test
    public void testFindByName() throws BuildingNotExistsException {
        Mockito.when(repository.findByName("TEST_BUILDING")).thenReturn(TEST_BUILDING);
        Building found = service.findByName("TEST_BUILDING");
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * A név alapján történő keresés nem létező épület kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByNameException() throws BuildingNotExistsException {
        service.findByName("EXCEPTION");
    }

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test
    public void testFindById() throws BuildingNotExistsException {
        Mockito.when(repository.findById(TEST_BUILDING.getId())).thenReturn(TEST_BUILDING);
        Building found = service.findById(TEST_BUILDING.getId());
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByIdException() throws BuildingNotExistsException {
        service.findById(1234);
    }

    /**
     * Az épületek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_BUILDING));
        List<Building> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_BUILDING, found.get(0));
    }

    /**
     * Az épületek neveinek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_BUILDING));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_BUILDING.getName(), found.get(0));
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_BUILDING.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_BUILDING.getId());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsById(TEST_BUILDING.getId())).thenReturn(false);
        exists = service.existsById(TEST_BUILDING.getId());
        Assert.assertFalse(exists);
    }

    /**
     * A név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_BUILDING.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_BUILDING.getName());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_BUILDING.getName())).thenReturn(false);
        exists = service.existsByName(TEST_BUILDING.getName());
        Assert.assertFalse(exists);
    }
}
