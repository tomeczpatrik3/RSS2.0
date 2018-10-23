/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.exception.UserAlredyExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.UserRepository;
import RoomReservationSystem.service.AuthorityService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Piti
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @InjectMocks
    UserServiceImpl service;
    
    @Mock
    AuthorityService aService;

    @Mock
    UserRepository repository;
    
    @Mock
    BCryptPasswordEncoder passwordEncoder;
    
    private static final User TEST_USER = new User(
            "testuser001",
            "testuser001",
            "TEST TEST",
            "testuser@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST
    );
    
    private static final UserDTO TEST_USERDTO = new UserDTO(
            "testuser001",
            "TEST TEST",
            "testuser@test.com",
            "testuser001"
    );

    /**
     * Test of loadUserByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testLoadUserByUsername() {
        Mockito.when(repository.findByUsername("testuser001")).thenReturn(TEST_USER);
        UserDetails found = service.loadUserByUsername("testuser001");
        assertNotNull(found);
        assertEquals(TEST_USER.getUsername(), found.getUsername());
        assertEquals(TEST_USER.getPassword(), found.getPassword());
    }

    /**
     * Test of register method, of class UserServiceImpl.
     */
//    @Test
//    public void testRegister() throws Exception {
//        Mockito.when(repository.save(TEST_USER)).thenReturn(TEST_USER);
//        Mockito.when(aService.findByName("ROLE_USER")).thenReturn(new Authority("ROLE_USER_TEST", Collections.EMPTY_LIST));
//        Mockito.when(passwordEncoder.encode(TEST_USERDTO.getPassword())).thenReturn("ENCODED_PW");
//        
//        User found = service.register(TEST_USERDTO);
//        assertNotNull(found);
//        assertEquals(TEST_USER.getUsername(), found.getUsername());
//        assertEquals(TEST_USER.getName(), found.getName());
//        assertEquals(TEST_USER.getEmail(), found.getEmail());
//    }
    
    @Test(expected = UserAlredyExistsException.class)
    public void testRegisterException() throws Exception {
        Mockito.when(repository.findByUsername("testuser001")).thenReturn(TEST_USER);
        service.register(TEST_USERDTO);
    }

    /**
     * Test of findByEmail method, of class UserServiceImpl.
     */
    @Test
    public void testFindByEmail() throws Exception {
        Mockito.when(repository.findByEmail("testuser@test.com")).thenReturn(TEST_USER);
        User found = service.findByEmail("testuser@test.com");
        assertNotNull(found);
        assertEquals(TEST_USER, found);
    }
    
    @Test(expected = UserNotExistsException.class)
    public void testFindByEmailException() throws Exception {
        service.findByEmail("EXCEPTION");
    }
    
    /**
     * Test of findByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testFindByUsername() throws Exception {
        Mockito.when(repository.findByUsername("testuser001")).thenReturn(TEST_USER);
        User found = service.findByUsername("testuser001");
        assertNotNull(found);
        assertEquals(TEST_USER, found);
    }
    
    @Test(expected = UserNotExistsException.class)
    public void testFindByUsernameException() throws Exception {
        service.findByUsername("EXCEPTION");
    }

    /**
     * Test of deleteByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteByUsername() throws Exception {
        Mockito.when(repository.findByUsername("testuser001")).thenReturn(TEST_USER);
        Mockito.doNothing().when(repository).deleteByUsername("testuser001");
        service.deleteByUsername("testuser001");
    }
    
    @Test(expected = UserNotExistsException.class)
    public void testDeleteByUsernameException() throws Exception {
        service.deleteByUsername("EXCEPTION");
    }

    /**
     * Test of findAll method, of class UserServiceImpl.
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_USER));
        List<User> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER, found.get(0));
    }

    /**
     * Test of findByName method, of class UserServiceImpl.
     */
    @Test
    public void testFindByName() {
        Mockito.when(repository.findByName("TEST TEST")).thenReturn(Arrays.asList(TEST_USER));
        List<User> found = service.findByName("TEST TEST");
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER, found.get(0));
    }

    /**
     * Test of getNames method, of class UserServiceImpl.
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_USER));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER.getName(), found.get(0));
    }
    
}
