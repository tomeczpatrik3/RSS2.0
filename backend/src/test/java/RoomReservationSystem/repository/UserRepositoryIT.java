/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.repository;

import RoomReservationSystem.model.User;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Piti
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIT {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;
    
    private static final User TEST_USER = new User(
            "testuser001",
            "testuser001",
            "TEST TEST",
            "testuser@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST
    );
    
    @Before
    public void setUp() {
        entityManager.persistAndFlush(TEST_USER);
    }
    
    @After
    public void tearDown() {
        entityManager.remove(TEST_USER);
        entityManager.flush();
    }

    /**
     * Test of findByUsername method, of class UserRepository.
     */
    @Test
    public void testFindByUsername() {
        User found = repository.findByUsername("testuser001");
        assertNotNull(found);
        assertEquals(TEST_USER, found);
        
        found = repository.findByUsername("noUserLikeThis");
        assertNull(found);
    }

    /**
     * Test of findByEmail method, of class UserRepository.
     */
    @Test
    public void testFindByEmail() {
        User found = repository.findByEmail("testuser@test.com");
        assertNotNull(found);
        assertEquals(TEST_USER, found);
        
        found = repository.findByEmail("noUserLikeThis@test.com");
        assertNull(found);
    }

//    /**
//     * Test of findByName method, of class UserRepository.
//     */
//    @Test
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "";
//        UserRepository instance = new UserRepositoryImpl();
//        List<User> expResult = null;
//        List<User> result = instance.findByName(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteByUsername method, of class UserRepository.
//     */
//    @Test
//    public void testDeleteByUsername() {
//        System.out.println("deleteByUsername");
//        String username = "";
//        UserRepository instance = new UserRepositoryImpl();
//        instance.deleteByUsername(username);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
