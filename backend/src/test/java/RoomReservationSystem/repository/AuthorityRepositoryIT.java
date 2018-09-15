package RoomReservationSystem.repository;

import RoomReservationSystem.model.Authority;
import java.util.Collections;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
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
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthorityRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorityRepository repository;

    private final String NAME = "TEST_AUTHORITY";
    private final Authority TEST_AUTH = new Authority(NAME, Collections.EMPTY_LIST);

    @Before
    public void setUp() {
        entityManager.persist(TEST_AUTH);
        entityManager.flush();
    }

    @After
    public void tearDown() {
        entityManager.remove(TEST_AUTH);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testFindByName() {
        Authority found = repository.findByName("TEST_AUTHORITY");

        //assertThat(found.getName()).isEqualTo(TEST_AUTH.getName());
        assertEquals(found.getName(), TEST_AUTH.getName());
        assertEquals(found.getId(), TEST_AUTH.getId());
        assertEquals(TEST_AUTH, found);
    }

    @Test
    public void testFindById() {
        Authority found = repository.findById(
                repository.findByName(NAME).getId());

        assertEquals(TEST_AUTH, found);
    }

//    @Test
//    public void testRemoveByName() {
//        repository.removeByName(NAME);
//        Authority found = repository.findByName(NAME);
//
//        assertNull(found);
//    }
}
