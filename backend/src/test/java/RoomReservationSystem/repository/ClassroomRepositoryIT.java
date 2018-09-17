/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.repository;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Tomecz Patrik
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassroomRepositoryIT {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClassroomRepository repository;
    
    private final Building TEST_BUILDING_1 = new Building("TESZT_EPULET_1", Collections.EMPTY_LIST);
    private final Building TEST_BUILDING_2 = new Building("TESZT_EPULET_2", Collections.EMPTY_LIST);
    private final Classroom TEST_CLASS_1 = new Classroom("TESZT_TEREM_1", true, true, 100, Collections.EMPTY_LIST, TEST_BUILDING_1);
    private final Classroom TEST_CLASS_2 = new Classroom("TESZT_TEREM_2", false, true, 30, Collections.EMPTY_LIST, TEST_BUILDING_2);
    private final Classroom TEST_CLASS_3 = new Classroom("TESZT_TEREM_1", true, false, 50, Collections.EMPTY_LIST, TEST_BUILDING_2);
    
    @Before
    public void setUp() {
        entityManager.persistAndFlush(TEST_BUILDING_1);
        entityManager.persistAndFlush(TEST_BUILDING_2);
        entityManager.persist(TEST_CLASS_1);
        entityManager.persist(TEST_CLASS_2);
        entityManager.persist(TEST_CLASS_3);
        entityManager.flush();
    }
    
    @After
    public void tearDown() {
        entityManager.clear();
    }

    /**
     * Test of findById method, of class ClassroomRepository.
     */
    @Test
    public void testFindById() {
        
    }

    /**
     * Test of findByNameAndBuilding method, of class ClassroomRepository.
     */
    @Test
    public void testFindByNameAndBuilding() {
        Classroom found = repository.findByNameAndBuilding("TESZT_TEREM_1", TEST_BUILDING_1);
        assertNotNull(found);
        assertEquals(TEST_CLASS_1, found);
        
        found = repository.findByNameAndBuilding("HIBA", TEST_BUILDING_1);
        assertNull(found);
        
        found = repository.findByNameAndBuilding("TESZT_TEREM_3", TEST_BUILDING_2);
        assertNull(found);
    }

    /**
     * Test of findByName method, of class ClassroomRepository.
     */
    @Test
    public void testFindByName() {
        List<Classroom> testList1 = repository.findByName("TESZT_TEREM_1");
        assertThat(testList1.size(), is(2));
        
        testList1 = repository.findByName("TESZT_TEREM_2");
        assertThat(testList1.size(), is(1));
        assertEquals(TEST_CLASS_2, testList1.get(0));
        
        testList1 = repository.findByName("URES");
        assertThat(testList1.size(), is(0));
    }

//    /**
//     * Test of findByHasPc method, of class ClassroomRepository.
//     */
//    @Test
//    public void testFindByHasPc() {
//        List<Classroom> testList1 = repository.findByHasPc(true);
//        assertThat(testList1.size(), is(2));
//        
//        List<Classroom> testList2 = repository.findByHasPc(false);
//        assertThat(testList2.size(), is(1));
//        assertEquals(TEST_CLASS_2, testList2.get(0));
//    }
//
//    /**
//     * Test of findByHasProjector method, of class ClassroomRepository.
//     */
//    @Test
//    public void testFindByHasProjector() {
//
//    }
//
//    /**
//     * Test of findByChairsLessThan method, of class ClassroomRepository.
//     */
//    @Test
//    public void testFindByChairsLessThan() {
//
//    }
//
//    /**
//     * Test of findByChairsGreaterThan method, of class ClassroomRepository.
//     */
//    @Test
//    public void testFindByChairsGreaterThan() {
//
//    }
//
//    /**
//     * Test of findByChairsBetween method, of class ClassroomRepository.
//     */
//    @Test
//    public void testFindByChairsBetween() {
//
//    }

    /**
     * Test of findByBuilding method, of class ClassroomRepository.
     */
    @Test
    public void testFindByBuilding() {
        List<Classroom> testList1 = repository.findByBuilding(TEST_BUILDING_1);
        assertThat(testList1.size(), is(1));
        assertEquals(TEST_CLASS_1, testList1.get(0));
        
        testList1 = repository.findByBuilding(TEST_BUILDING_2);
        assertThat(testList1.size(), is(2));
    }

    /**
     * Test of deleteByNameAndBuilding method, of class ClassroomRepository.
     */
    @Test
    public void testDeleteByNameAndBuilding() {
        Classroom deleteClass = new Classroom("TORLES", true, true, 100, Collections.EMPTY_LIST, TEST_BUILDING_1);
        entityManager.persistAndFlush(deleteClass);
        
        repository.deleteByNameAndBuilding("TORLES", TEST_BUILDING_1);
        
        Classroom found = repository.findByNameAndBuilding("TORLES", TEST_BUILDING_1);
        
        assertNull(found);
    }    
}
