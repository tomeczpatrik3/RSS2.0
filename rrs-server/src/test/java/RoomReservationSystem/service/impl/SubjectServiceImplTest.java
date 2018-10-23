/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.SubjectAlredyExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.repository.SubjectRepository;
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
public class SubjectServiceImplTest {

    @InjectMocks
    SubjectServiceImpl service;

    @Mock
    SubjectRepository repository;

    private static final Subject TEST_SUBJECT_1 = new Subject("TEST_SUBJECT", "TEST001", Collections.EMPTY_LIST);
    private static final Subject TEST_SUBJECT_2 = new Subject("TEST_SUBJECT", "TEST002", Collections.EMPTY_LIST);

    /**
     * Test of save method, of class SubjectServiceImpl.
     */
    @Test
    public void testSave() throws Exception {
        Mockito.when(repository.save(TEST_SUBJECT_1)).thenReturn(TEST_SUBJECT_1);
        Subject found = service.save(TEST_SUBJECT_1);
        assertNotNull(found);
        assertEquals(TEST_SUBJECT_1, found);
    }

    @Test(expected = SubjectAlredyExistsException.class)
    public void testSaveException() throws Exception {
        Mockito.when(repository.findByCode("TEST001")).thenReturn(TEST_SUBJECT_1);
        service.save(TEST_SUBJECT_1);
    }

    /**
     * Test of deleteByCode method, of class SubjectServiceImpl.
     */
    @Test
    public void testDeleteByCode() throws Exception {
        Mockito.when(repository.findByCode("TEST001")).thenReturn(TEST_SUBJECT_1);
        Mockito.doNothing().when(repository).deleteByCode("TEST001");
        service.deleteByCode("TEST001");
    }

    @Test(expected = SubjectNotExistsException.class)
    public void testDeleteByCodeException() throws Exception {
        service.deleteByCode("EXCEPTION");
    }

    /**
     * Test of findAll method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<Subject> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(2));
    }

    /**
     * Test of findByName method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindByName() {
        Mockito.when(repository.findByName("TEST_SUBJECT")).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<Subject> found = service.findByName("TEST_SUBJECT");
        assertNotNull(found);
        assertThat(found.size(), is(2));

        Mockito.when(repository.findByName("EMPTY")).thenReturn(Collections.EMPTY_LIST);
        found = service.findByName("EMPTY");
        assertNotNull(found);
        assertThat(found.size(), is(0));
    }

    /**
     * Test of findByCode method, of class SubjectServiceImpl.
     */
    @Test
    public void testFindByCode() throws Exception {
        Mockito.when(repository.findByCode("TEST001")).thenReturn(TEST_SUBJECT_1);
        Subject found = service.findByCode("TEST001");
        assertNotNull(found);
        assertEquals(TEST_SUBJECT_1, found);
    }

    @Test(expected = SubjectNotExistsException.class)
    public void testFindByCodeException() throws Exception {
        service.findByCode("EXCEPTION");
    }

    /**
     * Test of getSubjectNames method, of class SubjectServiceImpl.
     */
    @Test
    public void testGetSubjectNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2));
        List<String> found = service.getSubjectNames();
        assertNotNull(found);
        assertThat(found.size(), is(2));
    }
}
