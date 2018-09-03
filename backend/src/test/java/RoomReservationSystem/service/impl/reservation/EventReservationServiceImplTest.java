/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.reservation.EventReservation;
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
public class EventReservationServiceImplTest {
    
    public EventReservationServiceImplTest() {
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
     * Test of delete method, of class EventReservationServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        EventReservation reservation = null;
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        instance.delete(reservation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class EventReservationServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        EventReservationDTO eventReservationDTO = null;
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        EventReservation expResult = null;
        EventReservation result = instance.save(eventReservationDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class EventReservationServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        EventReservation expResult = null;
        EventReservation result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class EventReservationServiceImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        List<EventReservation> expResult = null;
        List<EventReservation> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class EventReservationServiceImpl.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        List<EventReservation> expResult = null;
        List<EventReservation> result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class EventReservationServiceImpl.
     */
    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        String statusName = "";
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        List<EventReservation> expResult = null;
        List<EventReservation> result = instance.findByStatus(statusName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class EventReservationServiceImpl.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int id = 0;
        String statusName = "";
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        EventReservation expResult = null;
        EventReservation result = instance.setStatus(id, statusName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClassroom method, of class EventReservationServiceImpl.
     */
    @Test
    public void testFindByClassroom() {
        System.out.println("findByClassroom");
        Classroom classroom = null;
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        List<EventReservation> expResult = null;
        List<EventReservation> result = instance.findByClassroom(classroom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class EventReservationServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        EventReservationServiceImpl instance = new EventReservationServiceImpl();
        EventReservation expResult = null;
        EventReservation result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
