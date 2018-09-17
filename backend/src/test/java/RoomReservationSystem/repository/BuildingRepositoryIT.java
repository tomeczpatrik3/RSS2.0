package RoomReservationSystem.repository;

import RoomReservationSystem.model.Building;
import java.util.Collections;
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
 * @author Tomecz Patrik
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BuildingRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BuildingRepository repository;
    
    private final String NAME = "TESZT_EPULET";
    private final Building TEST_BUILDING = new Building(NAME, Collections.EMPTY_LIST);
    

    @Before
    public void setUp() {
        entityManager.persist(TEST_BUILDING);
        entityManager.flush();
    }

    @After
    public void tearDown() {
        entityManager.clear();
    }

    /**
     * Test of findByName method, of class BuildingRepository.
     */
    @Test
    public void testFindByName() {
        Building found = repository.findByName(NAME);
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);

        found = repository.findByName("HIBA");
        assertNull(found);
    }

    /**
     * Test of findById method, of class BuildingRepository.
     */
    @Test
    public void testFindById() {
        int id = repository.findByName(NAME).getId();
        Building found = repository.findById(id);
        
        assertNotNull(found);
        assertEquals(TEST_BUILDING, found);
    }

    /**
     * Test of deleteByName method, of class BuildingRepository.
     */
    @Test
    public void testDeleteByName() {
        Building testRemove = new Building("TESZT_TORLES", Collections.EMPTY_LIST);
        entityManager.persistAndFlush(testRemove);
        
        repository.deleteByName("TESZT_TORLES");
        
        Building found = repository.findByName("TESZT_TORLES");
        
        assertNull(found);
    }
}
