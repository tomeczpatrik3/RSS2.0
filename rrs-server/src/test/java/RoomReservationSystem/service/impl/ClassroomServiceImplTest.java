package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomAlredyExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.repository.ClassroomRepository;
import RoomReservationSystem.service.BuildingService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Piti
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassroomServiceImplTest {

    @InjectMocks
    ClassroomServiceImpl service;

    @Mock
    ClassroomRepository cRepository;

    @Mock
    BuildingService bService;

    private final Building TEST_BUILDING_1 = new Building("TESZT_EPULET_1", Collections.EMPTY_LIST, 1);
    private final Building TEST_BUILDING_2 = new Building("TESZT_EPULET_2", Collections.EMPTY_LIST, 2);
    private final Classroom TEST_CLASS_1 = new Classroom("TESZT_TEREM_1", true, true, 100, Collections.EMPTY_LIST, TEST_BUILDING_1, 1);
    private final Classroom TEST_CLASS_2 = new Classroom("TESZT_TEREM_2", false, true, 30, Collections.EMPTY_LIST, TEST_BUILDING_2, 2);
    private final Classroom TEST_CLASS_3 = new Classroom("TESZT_TEREM_1", true, false, 50, Collections.EMPTY_LIST, TEST_BUILDING_2, 3);

    /**
     * A mentés tesztelésére szolgáló függvény
     *
     * @throws ClassroomAlredyExistsException A lehetséges kivétel
     */
    @Test
    public void testSave() throws ClassroomAlredyExistsException {
        Mockito.when(cRepository.save(TEST_CLASS_1)).thenReturn(TEST_CLASS_1);
        Classroom found = service.save(TEST_CLASS_1);
        assertNotNull(found);
        assertEquals(TEST_CLASS_1, found);
    }

    /**
     * A mentés már létező tanterem kivétel kiváltásának tesztelésére szolgáló
     * függvény
     *
     * @throws ClassroomAlredyExistsException A lehetséges kivétel
     */
    @Test(expected = ClassroomAlredyExistsException.class)
    public void testSaveException() throws ClassroomAlredyExistsException {
        Mockito.when(cRepository.findByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_1)).thenReturn(TEST_CLASS_1);
        service.save(TEST_CLASS_1);
    }

    /**
     * A tanteremek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testFindAll() {
        Mockito.when(cRepository.findAll()).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2, TEST_CLASS_3));
        List<Classroom> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(3));

        Mockito.when(cRepository.findAll()).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     */
    @Test
    public void testFindByName() {
        Mockito.when(cRepository.findByName("TESZT_TEREM_1")).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));
        List<Classroom> found = service.findByName("TESZT_TEREM_1");
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(cRepository.findByName(TEST_CLASS_2.getName())).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findByName(TEST_CLASS_2.getName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * Az épület alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByBuildingName() throws Exception {
        Mockito.when(bService.findByName(TEST_BUILDING_1.getName())).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_1)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));

        List<Classroom> found = service.findByBuildingName(TEST_BUILDING_1.getName());
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(bService.findByName(TEST_BUILDING_2.getName())).thenReturn(TEST_BUILDING_2);
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_2)).thenReturn(Arrays.asList(TEST_CLASS_2));

        found = service.findByBuildingName(TEST_BUILDING_2.getName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * Az épület alapján történő keresés nem létező épület kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByBuildingNameException() throws Exception {
        Mockito.when(bService.findByName("EXCEPTION")).thenThrow(new BuildingNotExistsException("ex"));
        service.findByBuildingName("EXCEPTION");
    }

    /**
     * A PC alapján történő keresés tesztelésére szolgáló függvény
     */
    @Test
    public void testFindByHasPc() {
        Mockito.when(cRepository.findByHasPc(true)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));
        List<Classroom> found = service.findByHasPc(true);
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(cRepository.findByHasPc(false)).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findByHasPc(false);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * A projektor alapján történő keresés tesztelésére szolgáló függvény
     */
    @Test
    public void testFindByHasProjector() {
        Mockito.when(cRepository.findByHasProjector(true)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2));
        List<Classroom> found = service.findByHasProjector(true);
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(cRepository.findByHasProjector(false)).thenReturn(Arrays.asList(TEST_CLASS_3));
        found = service.findByHasProjector(false);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_3, found.get(0));
    }

    /**
     * A székek száma (kevesebb) alapján történő keresés tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testFindByChairsLessThan() {
        Mockito.when(cRepository.findByChairsLessThan(200)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2, TEST_CLASS_3));
        List<Classroom> found = service.findByChairsLessThan(200);
        assertNotNull(found);
        assertThat(found.size(), is(3));

        Mockito.when(cRepository.findByChairsLessThan(40)).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findByChairsLessThan(40);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));

        Mockito.when(cRepository.findByChairsLessThan(20)).thenReturn(Collections.EMPTY_LIST);
        found = service.findByChairsLessThan(20);
        assertNotNull(found);
        assertThat(found.size(), is(0));
    }

    /**
     * A székek száma (több) alapján történő keresés tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testFindByChairsGreaterThan() {
        Mockito.when(cRepository.findByChairsGreaterThan(200)).thenReturn(Collections.EMPTY_LIST);
        List<Classroom> found = service.findByChairsGreaterThan(200);
        assertNotNull(found);
        assertThat(found.size(), is(0));

        Mockito.when(cRepository.findByChairsGreaterThan(100)).thenReturn(Arrays.asList(TEST_CLASS_1));
        found = service.findByChairsGreaterThan(100);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_1, found.get(0));

        Mockito.when(cRepository.findByChairsGreaterThan(20)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2, TEST_CLASS_3));
        found = service.findByChairsGreaterThan(20);
        assertNotNull(found);
        assertThat(found.size(), is(3));
    }

    /**
     * A székek száma (között) alapján történő keresés tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testFindByChairsBetween() {
        Mockito.when(cRepository.findByChairsBetween(0, 200)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2, TEST_CLASS_3));
        List<Classroom> found = service.findByChairsBetween(0, 200);
        assertNotNull(found);
        assertThat(found.size(), is(3));

        Mockito.when(cRepository.findByChairsBetween(40, 60)).thenReturn(Arrays.asList(TEST_CLASS_3));
        found = service.findByChairsBetween(40, 60);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_3, found.get(0));

        Mockito.when(cRepository.findByChairsBetween(0, 10)).thenReturn(Collections.EMPTY_LIST);
        found = service.findByChairsBetween(0, 10);
        assertNotNull(found);
        assertThat(found.size(), is(0));
    }

    /**
     * Az épület és tanterem alapján történő keresés tesztelésére szolgáló
     * függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     * @throws ClassroomNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByNameAndBuildingName() throws BuildingNotExistsException, ClassroomNotExistsException {
        Mockito.when(bService.findByName("TESZT_EPULET_1")).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByNameAndBuilding("TESZT_EPULET_1", TEST_BUILDING_1)).thenReturn(TEST_CLASS_1);
        Classroom found = service.findByNameAndBuildingName("TESZT_EPULET_1", TEST_BUILDING_1.getName());
        assertNotNull(found);
        assertEquals(TEST_CLASS_1, found);
    }

    /**
     * Az épület és tanterem alapján történő keresés nem létező épület kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     * @throws ClassroomNotExistsException A lehetséges kivétel
     */
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByNameAndBuildingNameExceptionOne() throws BuildingNotExistsException, ClassroomNotExistsException {
        Mockito.when(bService.findByName(TEST_BUILDING_1.getName())).thenThrow(new BuildingNotExistsException("EX"));
        this.service.findByNameAndBuildingName(TEST_CLASS_1.getName(), TEST_BUILDING_1.getName());
    }

    /**
     * Az épület és tanterem alapján történő keresés nem létező tanterem kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     * @throws ClassroomNotExistsException A lehetséges kivétel
     */
    @Test(expected = ClassroomNotExistsException.class)
    public void testFindByNameAndBuildingNameExceptionTwo() throws BuildingNotExistsException, ClassroomNotExistsException {
        Mockito.when(bService.findByName(TEST_BUILDING_1.getName())).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_1)).thenReturn(null);
        service.findByNameAndBuildingName(TEST_CLASS_1.getName(), TEST_BUILDING_1.getName());
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(cRepository.existsById(TEST_CLASS_1.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_CLASS_1.getId());
        assertTrue(exists);

        Mockito.when(cRepository.existsById(TEST_CLASS_1.getId())).thenReturn(false);
        exists = service.existsById(TEST_CLASS_1.getId());
        assertFalse(exists);
    }

    /**
     * A név és épület alapján történő létezés ellenőrzésének tesztelésére
     * szolgáló függvény
     */
    @Test
    public void testExistsByNameAndBuilding() {
        Mockito.when(cRepository.existsByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_1)).thenReturn(true);
        boolean exists = service.existsByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_1);
        assertTrue(exists);

        Mockito.when(cRepository.existsByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_2)).thenReturn(false);
        exists = service.existsByNameAndBuilding(TEST_CLASS_1.getName(), TEST_BUILDING_2);
        assertFalse(exists);
    }

    /**
     * Egy adott épülethez tartozó tantermek neveinek lekérdezésének
     * tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test
    public void getNamesByBuilding() throws BuildingNotExistsException {
        Mockito.when(bService.findByName(TEST_BUILDING_1.getName())).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_1)).thenReturn(Arrays.asList(TEST_CLASS_1));
        List<String> found = service.getNamesByBuilding(TEST_BUILDING_1.getName());
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_1.getName(), found.get(0));
    }

    /**
     * Egy adott épülethez tartozó tantermek neveinek lekérdezése közben nem
     * létező épület kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws BuildingNotExistsException A lehetséges kivétel
     */
    @Test(expected = BuildingNotExistsException.class)
    public void getNamesByBuildingException() throws BuildingNotExistsException {
        Mockito.when(bService.findByName(TEST_BUILDING_1.getName())).thenThrow(new BuildingNotExistsException("EX"));
        service.getNamesByBuilding(TEST_BUILDING_1.getName());
    }
}
