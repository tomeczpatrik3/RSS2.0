/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.repository.BuildingRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
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
public class BuildingServiceImplTest {
    
    @InjectMocks
    BuildingServiceImpl service;

    @Mock
    BuildingRepository repository;
    
    private final Building TEST_BUILDING = new Building("TEST_BUILDING", Collections.EMPTY_LIST);

    /**
     * Test of deleteByName method, of class BuildingServiceImpl.
     */
    @Test
    public void testDeleteByName() throws Exception {
        Mockito.when(repository.findByName("TEST_BUILDING")).thenReturn(TEST_BUILDING);
        Mockito.doNothing().when(repository).deleteByName("TEST_BUILDING");
        service.deleteByName("TEST_BUILDING");
    }
    
    @Test(expected = BuildingNotExistsException.class)
    public void testDeleteByNameException() throws Exception {
        service.deleteByName("EXCEPTION");
    }

    /**
     * Test of save method, of class BuildingServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
        Mockito.when(repository.save(TEST_BUILDING)).thenReturn(TEST_BUILDING);
        Building found = service.save(TEST_BUILDING);
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * Test of findByName method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        Mockito.when(repository.findByName("TEST_BUILDING")).thenReturn(TEST_BUILDING);
        Building found = service.findByName("TEST_BUILDING");
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }
    
    @Test(expected = BuildingNotExistsException.class)
    public void testFindByNameException() throws Exception {
        service.findByName("EXCEPTION");
    }

    /**
     * Test of findAll method, of class BuildingServiceImpl.
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_BUILDING));
        List<Building> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_BUILDING, found.get(0));
    }

    /**
     * Test of getNames method, of class BuildingServiceImpl.
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_BUILDING));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_BUILDING.getName(), found.get(0));
    }
}
