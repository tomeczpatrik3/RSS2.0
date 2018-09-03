/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api.reservation;

import RoomReservationSystem.dto.reservation.ReservationDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Tomecz Patrik
 */
public class ReservationApiControllerTest {
    
    public ReservationApiControllerTest() {
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
     * Test of getAccepted method, of class ReservationApiController.
     */
    @Test
    public void testGetAccepted() {
        System.out.println("getAccepted");
        ReservationApiController instance = new ReservationApiControllerImpl();
        List expResult = null;
        List result = instance.getAccepted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class ReservationApiController.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        ReservationApiController instance = new ReservationApiControllerImpl();
        List expResult = null;
        List result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class ReservationApiController.
     */
    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        String status = "";
        ReservationApiController instance = new ReservationApiControllerImpl();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClassroomAndDate method, of class ReservationApiController.
     */
    @Test
    public void testFindByClassroomAndDate() {
        System.out.println("findByClassroomAndDate");
        String building = "";
        String classroom = "";
        String date = "";
        ReservationApiController instance = new ReservationApiControllerImpl();
        List expResult = null;
        List result = instance.findByClassroomAndDate(building, classroom, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class ReservationApiController.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int id = 0;
        String status = "";
        ReservationApiController instance = new ReservationApiControllerImpl();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.setStatus(id, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ReservationApiControllerImpl extends ReservationApiController {

        public <T extends ReservationDTO> List<T> getAccepted() {
            return null;
        }

        public <T extends ReservationDTO> List<T> findByUsername(String username) {
            return null;
        }

        public ResponseEntity findByStatus(String status) {
            return null;
        }

        public <T extends ReservationDTO> List<T> findByClassroomAndDate(String building, String classroom, String date) {
            return null;
        }

        public ResponseEntity setStatus(int id, String status) {
            return null;
        }
    }
    
}
