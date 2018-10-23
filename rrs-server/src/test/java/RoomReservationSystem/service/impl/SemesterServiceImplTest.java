/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SemesterAlredyExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.repository.SemesterRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
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
public class SemesterServiceImplTest {
    
    @InjectMocks
    SemesterServiceImpl service;

    @Mock
    SemesterRepository repository;
    
    private final Semester TEST_SEMESTER = new Semester(
            "2011-2012/1", 
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(), 
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            Collections.EMPTY_LIST
    );

    /**
     * Test of getAll method, of class SemesterServiceImpl.
     */
    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER));
        List<Semester> found = service.getAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER, found.get(0));
    }

    /**
     * Test of save method, of class SemesterServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
        Mockito.when(repository.save(TEST_SEMESTER)).thenReturn(TEST_SEMESTER);
        Semester found = service.save(TEST_SEMESTER);
        assertNotNull(found);
        assertEquals(TEST_SEMESTER, found);
    }
    
    @Test(expected = SemesterAlredyExistsException.class)
    public void testSaveException() throws Exception {
        Mockito.when(repository.findByName("2011-2012/1")).thenReturn(TEST_SEMESTER);
        service.save(TEST_SEMESTER);
    }

    /**
     * Test of getNames method, of class SemesterServiceImpl.
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SEMESTER));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_SEMESTER.getName(), found.get(0));
    }

    /**
     * Test of findByName method, of class SemesterServiceImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        Mockito.when(repository.findByName("2011-2012/1")).thenReturn(TEST_SEMESTER);
        Semester found = service.findByName("2011-2012/1");
        assertNotNull(found);
        assertEquals(TEST_SEMESTER, found);
    }
    
    @Test(expected = SemesterNotExistsException.class)
    public void testFindByNameException() throws Exception {
        service.findByName("EXCEPTION");
    }

    /**
     * Test of deleteByName method, of class SemesterServiceImpl.
     */
    @Test
    public void testDeleteByName() throws Exception {
        Mockito.when(repository.findByName("2011-2012/1")).thenReturn(TEST_SEMESTER);
        Mockito.doNothing().when(repository).deleteByName("2011-2012/1");
        service.deleteByName("2011-2012/1");
    }
    
    @Test(expected = SemesterNotExistsException.class)
    public void testDeleteByNameException() throws Exception {
        service.deleteByName("EXCEPTION");
    }
}
