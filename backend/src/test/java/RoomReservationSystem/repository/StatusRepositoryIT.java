/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomReservationSystem.repository;

import RoomReservationSystem.model.Status;
import java.util.Collections;
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
public class StatusRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StatusRepository repository;

    private final Status TEST_STATUS = new Status("TEST_STATUS", "This is a test", Collections.EMPTY_LIST);

    /**
     * Test of findByName method, of class StatusRepository.
     */
    @Test
    public void testFindByName() {
        entityManager.persistAndFlush(TEST_STATUS);
        
        Status found = repository.findByName("TEST_STATUS");
        assertNotNull(found);
        assertEquals(TEST_STATUS, found);
        
        found = repository.findByName("NULL");
        assertNull(found);
        
        entityManager.remove(TEST_STATUS);
        entityManager.flush();
    }
}
