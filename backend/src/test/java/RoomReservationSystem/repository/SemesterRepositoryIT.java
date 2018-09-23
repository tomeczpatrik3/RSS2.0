/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.repository;

import RoomReservationSystem.model.Semester;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
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
 * @author Tomecz Patrik
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SemesterRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SemesterRepository repository;

    private final Semester TEST_SEMESTER = new Semester(
            "2011-2012/1", 
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(), 
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            Collections.EMPTY_LIST
    );

    @Before
    public void setUp() {
        entityManager.persist(TEST_SEMESTER);
        entityManager.flush();
    }

    @After
    public void tearDown() {
        entityManager.remove(TEST_SEMESTER);
        entityManager.flush();
    }

    /**
     * Test of findByName method, of class SemesterRepository.
     */
    @Test
    public void testFindByName() {
        Semester found = repository.findByName("2011-2012/1");
        assertNotNull(found);
        assertEquals(TEST_SEMESTER, found);
        
        found = repository.findByName("2011-2012/2");
        assertNull(found);
    }

    /**
     * Test of deleteByName method, of class SemesterRepository.
     */
    @Test
    public void testDeleteByName() {
        Semester testRemove = new Semester(
            "TORLES", 
            new GregorianCalendar(2011, Calendar.SEPTEMBER, 3).getTime(), 
            new GregorianCalendar(2012, Calendar.FEBRUARY, 2).getTime(),
            Collections.EMPTY_LIST
         );
        entityManager.persistAndFlush(testRemove);
        
        repository.deleteByName("TORLES");
        assertNull(repository.findByName("TORLES"));
    }
}
