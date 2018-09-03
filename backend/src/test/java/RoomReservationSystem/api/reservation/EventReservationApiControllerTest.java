/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
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
public class EventReservationApiControllerTest {
    
    public EventReservationApiControllerTest() {
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
     * Test of getAccepted method, of class EventReservationApiController.
     */
    @Test
    public void testGetAccepted() {
        System.out.println("getAccepted");
        EventReservationApiController instance = new EventReservationApiController();
        List<EventReservationDTO> expResult = null;
        List<EventReservationDTO> result = instance.getAccepted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class EventReservationApiController.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        EventReservationApiController instance = new EventReservationApiController();
        List<EventReservationDTO> expResult = null;
        List<EventReservationDTO> result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class EventReservationApiController.
     */
    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        String status = "";
        EventReservationApiController instance = new EventReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClassroomAndDate method, of class EventReservationApiController.
     */
    @Test
    public void testFindByClassroomAndDate() {
        System.out.println("findByClassroomAndDate");
        String building = "";
        String classroom = "";
        String date = "";
        EventReservationApiController instance = new EventReservationApiController();
        List<EventReservationDTO> expResult = null;
        List<EventReservationDTO> result = instance.findByClassroomAndDate(building, classroom, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class EventReservationApiController.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int id = 0;
        String status = "";
        EventReservationApiController instance = new EventReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.setStatus(id, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReservation method, of class EventReservationApiController.
     */
    @Test
    public void testCreateReservation() {
        System.out.println("createReservation");
        EventReservationDTO eventReservationDTO = null;
        BindingResult bindingResult = null;
        EventReservationApiController instance = new EventReservationApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createReservation(eventReservationDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class EventReservationApiController.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        EventReservationApiController instance = new EventReservationApiController();
        EventReservationDTO expResult = null;
        EventReservationDTO result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
