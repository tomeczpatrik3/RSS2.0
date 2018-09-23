/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.repository;

import RoomReservationSystem.model.Subject;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * @author Piti
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubjectRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository repository;

    private static final Subject TEST_SUBJECT_1 = new Subject("TEST_SUBJECT", "TEST001", Collections.EMPTY_LIST);
    private static final Subject TEST_SUBJECT_2 = new Subject("TEST_SUBJECT", "TEST002", Collections.EMPTY_LIST);
    
        @Before
    public void setUp() {
        entityManager.persistAndFlush(TEST_SUBJECT_1);
        entityManager.persistAndFlush(TEST_SUBJECT_2);
    }

    @After
    public void tearDown() {
        entityManager.remove(TEST_SUBJECT_1);
        entityManager.remove(TEST_SUBJECT_2);
        entityManager.flush();
    }

    /**
     * Test of findByName method, of class SubjectRepository.
     */
    @Test
    public void testFindByName() {
        List<Subject> foundList = repository.findByName("TEST_SUBJECT");
        assertThat(foundList.size(), is(2));

        foundList = repository.findByName("ZERO_FOUND");
        assertThat(foundList.size(), is(0));

    }

    /**
     * Test of findByCode method, of class SubjectRepository.
     */
    @Test
    public void testFindByCode() {
        Subject found = repository.findByCode("TEST001");
        assertNotNull(found);
        assertEquals(TEST_SUBJECT_1, found);

        found = repository.findByCode("NOT_FOUND");
        assertNull(found);
    }

//    /**
//     * Test of deleteByCode method, of class SubjectRepository.
//     */
//    @Test
//    public void testDeleteByCode() {
//        Subject testRemove = new Subject("TORLES", "TORLES", Collections.EMPTY_LIST);
//        entityManager.persistAndFlush(testRemove);
//        
//        repository.deleteByCode("TORLES");
//        assertNull(repository.findByCode("TORLES"));
//    }
}
