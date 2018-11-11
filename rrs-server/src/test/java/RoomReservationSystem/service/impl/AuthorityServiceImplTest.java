package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Az engedélyekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceImplTest {

    @InjectMocks
    AuthorityServiceImpl service;

    @Mock
    AuthorityRepository repository;

    private final Authority TEST_AUTHORITY = new Authority("TEST_AUTH", Collections.EMPTY_LIST, 999);


    /**
     * A mentés tesztelésére szolgáló függvény
     */
    @Test
    public void testSave() {
        Mockito.when(repository.save(TEST_AUTHORITY)).thenReturn(TEST_AUTHORITY);
        assertEquals(TEST_AUTHORITY, service.save(TEST_AUTHORITY));
    }

    /**
     * A név alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test
    public void testFindByName() throws AuthorityNotExistsException {
        Mockito.when(repository.findByName(TEST_AUTHORITY.getName())).thenReturn(TEST_AUTHORITY);
        assertEquals(TEST_AUTHORITY, service.findByName(TEST_AUTHORITY.getName()));
    }

    /**
     * A név alapján történő keresés nem létező engedély kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test(expected = AuthorityNotExistsException.class)
    public void testFindByNameException() throws AuthorityNotExistsException {
        service.findByName("EXCEPTION");
    }

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test
    public void testFindById() throws AuthorityNotExistsException {
        Mockito.when(repository.findById(TEST_AUTHORITY.getId())).thenReturn(TEST_AUTHORITY);
        assertEquals(TEST_AUTHORITY, service.findById(TEST_AUTHORITY.getId()));
    }

    /**
     * Az azonosító alapján történő keresés nem létező engedély kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test(expected = AuthorityNotExistsException.class)
    public void testFindByIdException() throws AuthorityNotExistsException {
        Mockito.when(repository.findById(TEST_AUTHORITY.getId())).thenReturn(null);
        service.findById(TEST_AUTHORITY.getId());
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_AUTHORITY.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_AUTHORITY.getId());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsById(TEST_AUTHORITY.getId())).thenReturn(false);
        exists = service.existsById(TEST_AUTHORITY.getId());
        Assert.assertFalse(exists);
    }

    /**
     * Aa név alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsByName() {
        Mockito.when(repository.existsByName(TEST_AUTHORITY.getName())).thenReturn(true);
        boolean exists = service.existsByName(TEST_AUTHORITY.getName());
        Assert.assertTrue(exists);

        Mockito.when(repository.existsByName(TEST_AUTHORITY.getName())).thenReturn(false);
        exists = service.existsByName(TEST_AUTHORITY.getName());
        Assert.assertFalse(exists);
    }

    /**
     * A név alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test
    public void testRemoveByName() throws AuthorityNotExistsException {
        Mockito.when(repository.findByName(TEST_AUTHORITY.getName())).thenReturn(TEST_AUTHORITY);
        Mockito.doNothing().when(repository).removeByName(TEST_AUTHORITY.getName());

        service.removeByName(TEST_AUTHORITY.getName());
    }

    /**
     * A név alapján történő törlés nem létező engedély kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Test(expected = AuthorityNotExistsException.class)
    public void testRemoveByNameException() throws AuthorityNotExistsException {
        service.removeByName("EXCEPTION");
    }
}
