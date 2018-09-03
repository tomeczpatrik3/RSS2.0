/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.ClassReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.reservation.ClassReservation;
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
public class ClassReservationServiceImplTest {
    
    public ClassReservationServiceImplTest() {
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
     * Test of delete method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        ClassReservation reservation = null;
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        instance.delete(reservation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        ClassReservationDTO classReservationDTO = null;
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        ClassReservation expResult = null;
        ClassReservation result = instance.save(classReservationDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        ClassReservation expResult = null;
        ClassReservation result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        List<ClassReservation> expResult = null;
        List<ClassReservation> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        List<ClassReservation> expResult = null;
        List<ClassReservation> result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testFindByStatus() {
        System.out.println("findByStatus");
        String statusName = "";
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        List<ClassReservation> expResult = null;
        List<ClassReservation> result = instance.findByStatus(statusName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int id = 0;
        String statusName = "";
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        ClassReservation expResult = null;
        ClassReservation result = instance.setStatus(id, statusName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClassroom method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testFindByClassroom() {
        System.out.println("findByClassroom");
        Classroom classroom = null;
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        List<ClassReservation> expResult = null;
        List<ClassReservation> result = instance.findByClassroom(classroom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBySubject method, of class ClassReservationServiceImpl.
     */
    @Test
    public void testFindBySubject() {
        System.out.println("findBySubject");
        Subject subject = null;
        ClassReservationServiceImpl instance = new ClassReservationServiceImpl();
        List<ClassReservation> expResult = null;
        List<ClassReservation> result = instance.findBySubject(subject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
