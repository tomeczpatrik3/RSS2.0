/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorityServiceImplTest {
   
    private static final Integer AUTHORITY_ID = 99;
    private static final String AUTHORITY_NAME = "TEST_AUTHORITY";
    
    @Mock
    private AuthorityRepository repository;
    
    @InjectMocks
    private AuthorityServiceImpl service;
    
    private Authority authority;
    
    @Before
    public void setUp() {
        authority = new Authority();
        authority.setId(AUTHORITY_ID);
        authority.setName(AUTHORITY_NAME);
        authority.setUserList(Collections.EMPTY_LIST);
    }
    
    /**
     * Test of save method, of class AuthorityServiceImpl.
     */
    @Test
    public void testSave() {
        Mockito.when(service.save(any(Authority.class))).thenReturn(authority);
        
        Authority exceptedResult = service.save(authority);
        
        Assert.assertNotNull(exceptedResult);
        Assert.assertEquals(exceptedResult, authority);
    }

    /**
     * Test of delete method, of class AuthorityServiceImpl.
     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Authority authority = null;
//        AuthorityServiceImpl instance = new AuthorityServiceImpl();
//        instance.delete(authority);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of findByName method, of class AuthorityServiceImpl.
     */
//    @Test
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "";
//        AuthorityServiceImpl instance = new AuthorityServiceImpl();
//        Authority expResult = null;
//        Authority result = instance.findByName(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of findById method, of class AuthorityServiceImpl.
     */
//    @Test
//    public void testFindById() {
//        System.out.println("findById");
//        int id = 0;
//        AuthorityServiceImpl instance = new AuthorityServiceImpl();
//        Authority expResult = null;
//        Authority result = instance.findById(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
