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
    
    private final Building TEST_BUILDING_1 = new Building("TESZT_EPULET_1", Collections.EMPTY_LIST);
    private final Building TEST_BUILDING_2 = new Building("TESZT_EPULET_2", Collections.EMPTY_LIST);
    private final Classroom TEST_CLASS_1 = new Classroom("TESZT_TEREM_1", true, true, 100, Collections.EMPTY_LIST, TEST_BUILDING_1);
    private final Classroom TEST_CLASS_2 = new Classroom("TESZT_TEREM_2", false, true, 30, Collections.EMPTY_LIST, TEST_BUILDING_2);
    private final Classroom TEST_CLASS_3 = new Classroom("TESZT_TEREM_1", true, false, 50, Collections.EMPTY_LIST, TEST_BUILDING_2);


//    /**
//     * Test of deleteByNameAndBuildingName method, of class ClassroomServiceImpl.
//     */
//    @Test
//    public void testDeleteByNameAndBuildingName() throws Exception {
//        System.out.println("deleteByNameAndBuildingName");
//        String name = "";
//        String buildingName = "";
//        ClassroomServiceImpl instance = new ClassroomServiceImpl();
//        instance.deleteByNameAndBuildingName(name, buildingName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of save method, of class ClassroomServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
        Mockito.when(cRepository.save(TEST_CLASS_1)).thenReturn(TEST_CLASS_1);
        Classroom found = service.save(TEST_CLASS_1);
        assertNotNull(found);
        assertEquals(TEST_CLASS_1, found);
    }
    
    @Test(expected = ClassroomAlredyExistsException.class)
    public void testSaveException() throws Exception {
        Mockito.when(cRepository.findByNameAndBuilding("TESZT_TEREM_1", TEST_BUILDING_1)).thenReturn(TEST_CLASS_1);
        service.save(TEST_CLASS_1);
    }    

    /**
     * Test of findAll method, of class ClassroomServiceImpl.
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
     * Test of findByName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByName() {
        Mockito.when(cRepository.findByName("TESZT_TEREM_1")).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));
        List<Classroom> found = service.findByName("TESZT_TEREM_1");
        assertNotNull(found);
        assertThat(found.size(), is(2));
        
        Mockito.when(cRepository.findByName("TESZT_TEREM_2")).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findByName("TESZT_TEREM_2");
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * Test of findByBuildingName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByBuildingName() throws Exception {
        Mockito.when(bService.findByName("TESZT_EPULET_1")).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_1)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));

        List<Classroom> found = service.findByBuildingName("TESZT_EPULET_1");
        assertNotNull(found);
        assertThat(found.size(), is(2));
        
        Mockito.when(bService.findByName("TESZT_EPULET_2")).thenReturn(TEST_BUILDING_2);
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_2)).thenReturn(Arrays.asList(TEST_CLASS_2));
        
        found = service.findByBuildingName("TESZT_EPULET_2");
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }
    
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByBuildingNameException() throws Exception {
        Mockito.when(bService.findByName("EXCEPTION")).thenThrow(new BuildingNotExistsException("ex"));
        service.findByBuildingName("EXCEPTION");
    }

    /**
     * Test of findByBuilding method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByBuilding() {
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_1)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_3));
        List<Classroom> found = service.findByBuilding(TEST_BUILDING_1);
        assertNotNull(found);
        assertThat(found.size(), is(2));
        
        Mockito.when(cRepository.findByBuilding(TEST_BUILDING_2)).thenReturn(Arrays.asList(TEST_CLASS_2));
        found = service.findByBuilding(TEST_BUILDING_2);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_2, found.get(0));
    }

    /**
     * Test of findByHasPc method, of class ClassroomServiceImpl.
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
     * Test of findByHasProjector method, of class ClassroomServiceImpl.
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
     * Test of findByChairsLessThan method, of class ClassroomServiceImpl.
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
     * Test of findByChairsGreaterThan method, of class ClassroomServiceImpl.
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
     * Test of findByChairsBetween method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByChairsBetween() {
        Mockito.when(cRepository.findByChairsBetween(0, 200)).thenReturn(Arrays.asList(TEST_CLASS_1, TEST_CLASS_2, TEST_CLASS_3));
        List<Classroom> found = service.findByChairsBetween(0,200);
        assertNotNull(found);
        assertThat(found.size(), is(3));
        
        Mockito.when(cRepository.findByChairsBetween(40, 60)).thenReturn(Arrays.asList(TEST_CLASS_3));
        found = service.findByChairsBetween(40,60);
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_CLASS_3, found.get(0));
        
        Mockito.when(cRepository.findByChairsBetween(0, 10)).thenReturn(Collections.EMPTY_LIST);
        found = service.findByChairsBetween(0, 10);
        assertNotNull(found);
        assertThat(found.size(), is(0));
    }

    /**
     * Test of findByNameAndBuildingName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByNameAndBuildingName() throws Exception {
        Mockito.when(bService.findByName("TESZT_EPULET_1")).thenReturn(TEST_BUILDING_1);
        Mockito.when(cRepository.findByNameAndBuilding("TESZT_EPULET_1", TEST_BUILDING_1)).thenReturn(TEST_CLASS_1);
        Classroom found = service.findByNameAndBuildingName("TESZT_EPULET_1", TEST_BUILDING_1.getName());
        assertNotNull(found);
        assertEquals(TEST_CLASS_1, found);
    }
    
    @Test(expected = ClassroomNotExistsException.class)
    public void testFindByNameAndBuildingNameException() throws Exception {
        service.findByNameAndBuildingName("EXCEPTION", "EXCEPTION");
    }
}