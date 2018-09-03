/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
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
public class SubjectServiceImplTest {
    
    public SubjectServiceImplTest() {
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
     * Test of save method, of class SubjectServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Subject subject = null;
        SubjectServiceImpl instance = new SubjectServiceImpl();
        Subject expResult = null;
        Subject result = instance.save(subject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class SubjectServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Subject subject = null;
        SubjectServiceImpl instance = new SubjectServiceImpl();
        instance.delete(subject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByCode method, of class SubjectServiceImpl.
     */
    @Test
    public void testDeleteByCode() {
        System.out.println("deleteByCode");
        String code = "";
        SubjectServiceImpl instance = new SubjectServiceImpl();
        instance.deleteByCode(code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        SubjectServiceImpl instance = new SubjectServiceImpl();
        List<Subject> expResult = null;
        List<Subject> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        SubjectServiceImpl instance = new SubjectServiceImpl();
        Subject expResult = null;
        Subject result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        SubjectServiceImpl instance = new SubjectServiceImpl();
        List<Subject> expResult = null;
        List<Subject> result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCode method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindByCode() {
        System.out.println("findByCode");
        String code = "";
        SubjectServiceImpl instance = new SubjectServiceImpl();
        Subject expResult = null;
        Subject result = instance.findByCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjectNames method, of class SubjectServiceImpl.
     */
    @Test
    public void testGetSubjectNames() {
        System.out.println("getSubjectNames");
        SubjectServiceImpl instance = new SubjectServiceImpl();
        List<String> expResult = null;
        List<String> result = instance.getSubjectNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDTO method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindByDTO() {
        System.out.println("findByDTO");
        SubjectDTO subjectDTO = null;
        SubjectServiceImpl instance = new SubjectServiceImpl();
        Subject expResult = null;
        Subject result = instance.findByDTO(subjectDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
