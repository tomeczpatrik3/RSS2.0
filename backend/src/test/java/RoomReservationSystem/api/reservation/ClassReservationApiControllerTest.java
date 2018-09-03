/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.reservation.ClassReservationDTO;
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
public class ClassReservationApiControllerTest {
    
    public ClassReservationApiControllerTest() {
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
     * Test of getAccepted method, of class ClassReservationApiController.
     */
    @Test
    public void testGetAccepted() {
        System.out.println("getAccepted");
        ClassReservationApiController instance = new ClassReservationApiController();
        List<ClassReservationDTO> expResult = null;
        List<ClassReservationDTO> result = instance.getAccepted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class ClassReservationApiController.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        ClassReservationApiController instance = new ClassReservationApiController();
        List<ClassReservationDTO> expResult = null;
        List<ClassReservationDTO> result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class ClassReservationApiController.
     */
    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        String status = "";
        ClassReservationApiController instance = new ClassReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClassroomAndDate method, of class ClassReservationApiController.
     */
    @Test
    public void testFindByClassroomAndDate() {
        System.out.println("findByClassroomAndDate");
        String building = "";
        String classroom = "";
        String date = "";
        ClassReservationApiController instance = new ClassReservationApiController();
        List<ClassReservationDTO> expResult = null;
        List<ClassReservationDTO> result = instance.findByClassroomAndDate(building, classroom, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class ClassReservationApiController.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int id = 0;
        String status = "";
        ClassReservationApiController instance = new ClassReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.setStatus(id, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReservation method, of class ClassReservationApiController.
     */
    @Test
    public void testCreateReservation() {
        System.out.println("createReservation");
        ClassReservationDTO classReservationDTO = null;
        BindingResult bindingResult = null;
        ClassReservationApiController instance = new ClassReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createReservation(classReservationDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
