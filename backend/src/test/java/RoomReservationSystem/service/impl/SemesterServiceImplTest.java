/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SemesterDTO;
import RoomReservationSystem.model.Semester;
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
public class SemesterServiceImplTest {
    
    public SemesterServiceImplTest() {
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
     * Test of getAll method, of class SemesterServiceImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        SemesterServiceImpl instance = new SemesterServiceImpl();
        List<Semester> expResult = null;
        List<Semester> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class SemesterServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Semester semester = null;
        SemesterServiceImpl instance = new SemesterServiceImpl();
        Semester expResult = null;
        Semester result = instance.save(semester);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class SemesterServiceImpl.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        SemesterServiceImpl instance = new SemesterServiceImpl();
        List<String> expResult = null;
        List<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class SemesterServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        SemesterServiceImpl instance = new SemesterServiceImpl();
        Semester expResult = null;
        Semester result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class SemesterServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Semester semester = null;
        SemesterServiceImpl instance = new SemesterServiceImpl();
        instance.delete(semester);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class SemesterServiceImpl.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        SemesterServiceImpl instance = new SemesterServiceImpl();
        instance.deleteByName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDTO method, of class SemesterServiceImpl.
     */
    @Test
    public void testFindByDTO() {
        System.out.println("findByDTO");
        SemesterDTO semesterDTO = null;
        SemesterServiceImpl instance = new SemesterServiceImpl();
        Semester expResult = null;
        Semester result = instance.findByDTO(semesterDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
