/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Classroom;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Tomecz Patrik
 */
public class ClassroomApiControllerTest {
    
    public ClassroomApiControllerTest() {
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
     * Test of getAll method, of class ClassroomApiController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class ClassroomApiController.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByBuildingName method, of class ClassroomApiController.
     */
    @Test
    public void testFindByBuildingName() {
        System.out.println("findByBuildingName");
        String buildingName = "";
        ClassroomApiController instance = new ClassroomApiController();
        List<Classroom> expResult = null;
        List<Classroom> result = instance.findByBuildingName(buildingName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByHasPC method, of class ClassroomApiController.
     */
    @Test
    public void testFindByHasPC() {
        System.out.println("findByHasPC");
        boolean hasPC = false;
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByHasPC(hasPC);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByHasProjector method, of class ClassroomApiController.
     */
    @Test
    public void testFindByHasProjector() {
        System.out.println("findByHasProjector");
        boolean hasProjector = false;
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByHasProjector(hasProjector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsLessThan method, of class ClassroomApiController.
     */
    @Test
    public void testFindByChairsLessThan() {
        System.out.println("findByChairsLessThan");
        int chairs = 0;
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByChairsLessThan(chairs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsGreaterThan method, of class ClassroomApiController.
     */
    @Test
    public void testFindByChairsGreaterThan() {
        System.out.println("findByChairsGreaterThan");
        int chairs = 0;
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByChairsGreaterThan(chairs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByChairsBetween method, of class ClassroomApiController.
     */
    @Test
    public void testFindByChairsBetween() {
        System.out.println("findByChairsBetween");
        int from = 0;
        int to = 0;
        ClassroomApiController instance = new ClassroomApiController();
        List<ClassroomDTO> expResult = null;
        List<ClassroomDTO> result = instance.findByChairsBetween(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClassroom method, of class ClassroomApiController.
     */
    @Test
    public void testCreateClassroom() {
        System.out.println("createClassroom");
        ClassroomDTO classroomDTO = null;
        BindingResult bindingResult = null;
        ClassroomApiController instance = new ClassroomApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createClassroom(classroomDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByRoomName method, of class ClassroomApiController.
     */
    @Test
    public void testDeleteByRoomName() {
        System.out.println("deleteByRoomName");
        String name = "";
        ClassroomApiController instance = new ClassroomApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteByRoomName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClassroom method, of class ClassroomApiController.
     */
    @Test
    public void testUpdateClassroom() {
        System.out.println("updateClassroom");
        ClassroomDTO classroomDTO = null;
        BindingResult bindingResult = null;
        ClassroomApiController instance = new ClassroomApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.updateClassroom(classroomDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
