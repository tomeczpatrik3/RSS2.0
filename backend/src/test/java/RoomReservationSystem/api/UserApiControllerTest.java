/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.api;

import RoomReservationSystem.dto.UserDTO;
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
public class UserApiControllerTest {
    
    public UserApiControllerTest() {
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
     * Test of getAll method, of class UserApiController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        UserApiController instance = new UserApiController();
        List<UserDTO> expResult = null;
        List<UserDTO> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class UserApiController.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        UserApiController instance = new UserApiController();
        List<String> expResult = null;
        List<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class UserApiController.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        UserApiController instance = new UserApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class UserApiController.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        UserApiController instance = new UserApiController();
        List<UserDTO> expResult = null;
        List<UserDTO> result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserApiController.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        UserDTO userDTO = null;
        BindingResult bindingResult = null;
        UserApiController instance = new UserApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.createUser(userDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UserApiController.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        UserDTO userDTO = null;
        BindingResult bindingResult = null;
        UserApiController instance = new UserApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.updateUser(userDTO, bindingResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByUsername method, of class UserApiController.
     */
    @Test
    public void testDeleteByUsername() {
        System.out.println("deleteByUsername");
        String username = "";
        UserApiController instance = new UserApiController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
