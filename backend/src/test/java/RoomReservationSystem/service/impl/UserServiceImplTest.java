/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Tomecz Patrik
 */
public class UserServiceImplTest {
    
    public UserServiceImplTest() {
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
     * Test of loadUserByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testLoadUserByUsername() {
        System.out.println("loadUserByUsername");
        String username = "";
        UserServiceImpl instance = new UserServiceImpl();
        UserDetails expResult = null;
        UserDetails result = instance.loadUserByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class UserServiceImpl.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        UserDTO userDTO = null;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.register(userDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEmail method, of class UserServiceImpl.
     */
    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        String email = "";
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.findByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class UserServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        String username = "";
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteByUsername() {
        System.out.println("deleteByUsername");
        String username = "";
        UserServiceImpl instance = new UserServiceImpl();
        instance.deleteByUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.delete(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class UserServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserServiceImpl instance = new UserServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class UserServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        UserServiceImpl instance = new UserServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class UserServiceImpl.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        UserServiceImpl instance = new UserServiceImpl();
        List<String> expResult = null;
        List<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDTO method, of class UserServiceImpl.
     */
    @Test
    public void testFindByDTO() {
        System.out.println("findByDTO");
        UserDTO userDTO = null;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.findByDTO(userDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
