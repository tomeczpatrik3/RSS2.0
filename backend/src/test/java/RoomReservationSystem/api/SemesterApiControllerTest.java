/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api;

import RoomReservationSystem.dto.SemesterDTO;
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
public class SemesterApiControllerTest {
    
    public SemesterApiControllerTest() {
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
     * Test of getAll method, of class SemesterApiController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        SemesterApiController instance = new SemesterApiController();
        List<SemesterDTO> expResult = null;
        List<SemesterDTO> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSemesterNames method, of class SemesterApiController.
     */
    @Test
    public void testGetSemesterNames() {
        System.out.println("getSemesterNames");
        SemesterApiController instance = new SemesterApiController();
        List<String> expResult = null;
        List<String> result = instance.getSemesterNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSemester method, of class SemesterApiController.
     */
    @Test
    public void testCreateSemester() {
        System.out.println("createSemester");
        SemesterDTO semesterDTO = null;
        BindingResult bindingResult = null;
        SemesterApiController instance = new SemesterApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createSemester(semesterDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class SemesterApiController.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        SemesterApiController instance = new SemesterApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
