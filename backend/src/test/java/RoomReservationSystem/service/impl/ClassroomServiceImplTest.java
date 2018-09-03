/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomecz Patrik
 */
public class ClassroomServiceImplTest {
    
    public ClassroomServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of delete method, of class ClassroomServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Classroom classroom = null;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        instance.delete(classroom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        instance.deleteByName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ClassroomServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Classroom classroom = null;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        Classroom expResult = null;
        Classroom result = instance.save(classroom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        Classroom expResult = null;
        Classroom result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByBuildingName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByBuildingName() {
        System.out.println("findByBuildingName");
        String buildingName = "";
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByBuildingName(buildingName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByBuilding method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByBuilding() {
        System.out.println("findByBuilding");
        Building building = null;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByBuilding(building);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByHasPc method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByHasPc() {
        System.out.println("findByHasPc");
        boolean hasPC = false;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByHasPc(hasPC);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByHasProjector method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByHasProjector() {
        System.out.println("findByHasProjector");
        boolean hasProjector = false;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByHasProjector(hasProjector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsLessThan method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByChairsLessThan() {
        System.out.println("findByChairsLessThan");
        int num = 0;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByChairsLessThan(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsGreaterThan method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByChairsGreaterThan() {
        System.out.println("findByChairsGreaterThan");
        int num = 0;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByChairsGreaterThan(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsBetween method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByChairsBetween() {
        System.out.println("findByChairsBetween");
        int from = 0;
        int to = 0;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByChairsBetween(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByNameAndBuildingName method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByNameAndBuildingName() {
        System.out.println("findByNameAndBuildingName");
        String name = "";
        String buildingName = "";
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        Classroom expResult = null;
        Classroom result = instance.findByNameAndBuildingName(name, buildingName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDTO method, of class ClassroomServiceImpl.
     */
    @Test
    public void testFindByDTO() {
        System.out.println("findByDTO");
        ClassroomDTO classroomDTO = null;
        ClassroomServiceImpl instance = new ClassroomServiceImpl();
        Classroom expResult = null;
        Classroom result = instance.findByDTO(classroomDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
