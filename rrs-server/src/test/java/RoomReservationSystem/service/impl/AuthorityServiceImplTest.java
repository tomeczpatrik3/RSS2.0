/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Piti
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceImplTest {

    @InjectMocks
    AuthorityServiceImpl service;

    @Mock
    AuthorityRepository repository;

    private final Authority TEST_AUTHORITY = new Authority("TEST_AUTH", Collections.EMPTY_LIST);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class AuthorityServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
        Mockito.when(repository.save(TEST_AUTHORITY)).thenReturn(TEST_AUTHORITY);
        assertEquals(TEST_AUTHORITY, service.save(TEST_AUTHORITY));
    }

    /**
     * Test of findByName method, of class AuthorityServiceImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        Mockito.when(repository.findByName("TEST_AUTH")).thenReturn(TEST_AUTHORITY);

        assertEquals(TEST_AUTHORITY, service.findByName("TEST_AUTH"));
    }

    @Test(expected = AuthorityNotExistsException.class)
    public void testFindByNameException() throws Exception {
        service.findByName("EXCEPTION");
    }

    /**
     * Test of removeByName method, of class AuthorityServiceImpl.
     */
    @Test
    public void testRemoveByName() throws Exception {
        Mockito.when(repository.findByName("TEST_AUTH")).thenReturn(TEST_AUTHORITY);
        Mockito.doNothing().when(repository).removeByName("TEST_AUTH");

        service.removeByName("TEST_AUTH");
    }

    @Test(expected = AuthorityNotExistsException.class)
    public void testRemoveByNameException() throws Exception {
        service.removeByName("EXCEPTION");
    }

}
