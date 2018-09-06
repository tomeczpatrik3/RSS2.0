/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Authority;
import java.util.Collections;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityServiceImplTest {
    private static final String AUTHORITY_NAME = "TEST_AUTH";

    @Autowired
    private AuthorityServiceImpl service;

    private Authority authority;
    private Authority exceptedResult;

    @Before
    public void setUp() {
        authority = new Authority();
        authority.setName(AUTHORITY_NAME);
        authority.setUserList(Collections.EMPTY_LIST);

        exceptedResult = null;
    }

    /**
     * Test of save method, of class AuthorityServiceImpl.
     *
     * @throws RoomReservationSystem.exception.AuthorityAlredyExistsException
     */
    @Test()
    public void testSave() throws Exception {        
        exceptedResult = service.save(authority);

        //Ellenőrzések:
        Assert.assertNotNull(exceptedResult);
        Assert.assertEquals(exceptedResult, authority);
        
        service.removeByName(exceptedResult.getName());
    }

//    @Test(expected = InvalidParameterException.class)
//    public void testSaveInvalidParameterException() throws Exception {
//        Mockito.when(service.save(null)).thenThrow(new InvalidParameterException(""));
//        service.save(null);
//    }
    /**
     * Test of delete method, of class AuthorityServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDelete() throws Exception{        
        exceptedResult = service.save(authority);
        service.removeByName(exceptedResult.getName());
    }
    /**
     * Test of findByName method, of class AuthorityServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindByName() throws Exception {
        service.save(authority);
        exceptedResult = service.findByName(AUTHORITY_NAME);
        
        assertNotNull(exceptedResult);
        assertEquals(authority, exceptedResult);
        
        service.removeByName(exceptedResult.getName());
    }
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
