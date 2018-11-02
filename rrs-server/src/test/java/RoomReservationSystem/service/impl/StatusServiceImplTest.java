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
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByName() throws StatusNotExistsException {
        Mockito.when(repository.findByName(TEST_STATUS.getName())).thenReturn(TEST_STATUS);
        Status found = service.findByName(TEST_STATUS.getName());
        assertEquals(TEST_STATUS, found);
    }
    
    /**
     * A név alapján történő keresés nem létező státusz kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws StatusNotExistsException A lehetséges kivétel
     */
    @Test(expected = StatusNotExistsException.class)
    public void testFindByNameException() throws StatusNotExistsException {
        service.findByName("EXCEPTION");
    }
    
    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_STATUS.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_STATUS.getId());
        assertTrue(exists);

        Mockito.when(repository.existsById(TEST_STATUS.getId())).thenReturn(false);
        exists = service.existsById(TEST_STATUS.getId());
        assertFalse(exists);
    }

    /**
     * A név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_STATUS.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_STATUS.getName());
        assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_STATUS.getName())).thenReturn(false);
        exists = service.existsByName(TEST_STATUS.getName());
        assertFalse(exists);
    }
}
