/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api;

import RoomReservationSystem.dto.SubjectDTO;
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
public class SubjectApiControllerTest {
    
    public SubjectApiControllerTest() {
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
     * Test of getAll method, of class SubjectApiController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        SubjectApiController instance = new SubjectApiController();
        List<SubjectDTO> expResult = null;
        List<SubjectDTO> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjectNames method, of class SubjectApiController.
     */
    @Test
    public void testGetSubjectNames() {
        System.out.println("getSubjectNames");
        SubjectApiController instance = new SubjectApiController();
        List<String> expResult = null;
        List<String> result = instance.getSubjectNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSubject method, of class SubjectApiController.
     */
    @Test
    public void testCreateSubject() {
        System.out.println("createSubject");
        SubjectDTO subjectDTO = null;
        BindingResult bindingResult = null;
        SubjectApiController instance = new SubjectApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createSubject(subjectDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSubject method, of class SubjectApiController.
     */
    @Test
    public void testUpdateSubject() {
        System.out.println("updateSubject");
        SubjectDTO subjectDTO = null;
        BindingResult bindingResult = null;
        SubjectApiController instance = new SubjectApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.updateSubject(subjectDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByCode method, of class SubjectApiController.
     */
    @Test
    public void testDeleteByCode() {
        System.out.println("deleteByCode");
        String code = "";
        SubjectApiController instance = new SubjectApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteByCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
