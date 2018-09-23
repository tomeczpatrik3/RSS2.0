/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.repository.StatusRepository;
import java.util.Collections;
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
public class StatusServiceImplTest {
    
    @InjectMocks
    StatusServiceImpl service;

    @Mock
    StatusRepository repository;
    
    private final Status TEST_STATUS = new Status("TEST_STATUS", "This is a test", Collections.EMPTY_LIST);  
    
    /**
     * Test of findByName method, of class StatusServiceImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        Mockito.when(repository.findByName("TEST_STATUS")).thenReturn(TEST_STATUS);
        Status found = service.findByName("TEST_STATUS");
        assertNotNull(found);
        assertEquals(TEST_STATUS, found);
    }
    
    @Test(expected = StatusNotExistsException.class)
    public void testFindByNameException() throws Exception {
        service.findByName("EXCEPTION");
    }
    
}
