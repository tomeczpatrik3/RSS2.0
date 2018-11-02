package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.exception.UserAlredyExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.UserRepository;
import RoomReservationSystem.service.AuthorityService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Piti
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl service;

    @Mock
    AuthorityService aService;

    @Mock
    UserRepository repository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    private static final User TEST_USER = new User(
            "testuser001",
            "testuser001",
            "TEST TEST",
            "testuser@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            1
    );

    private static final UserDTO TEST_USER_DTO = new UserDTO(
            "testuser001",
            "TEST TEST",
            "testuser@test.com",
            "testuser001"
    );

    private static final Authority USER_ROLE = new Authority(
            "ROLE_USER",
            Collections.EMPTY_LIST
    );

    /**
     * A felhasználónév alapján történő betöltés tesztelésére szolgáló függvény
     */
    @Test
    public void testLoadUserByUsername() {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        UserDetails found = service.loadUserByUsername(TEST_USER.getUsername());

        assertEquals(TEST_USER.getUsername(), found.getUsername());
    }

    /**
     * A regisztrálás tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel
     * @throws UserAlredyExistsException A lehetséges kivétel
     * @throws AuthorityAlredyExistsException A lehetséges kivétel
     */
//    @Test
//    public void testRegister() throws AuthorityNotExistsException, UserAlredyExistsException, AuthorityAlredyExistsException {
//        Mockito.when(repository.findByUsername(TEST_USER_DTO.getUsername())).thenReturn(null);
//        Mockito.when(repository.findByEmail(TEST_USER_DTO.getEmail())).thenReturn(null);
//        Mockito.when(aService.findByName(USER_ROLE.getName())).thenReturn(USER_ROLE);
//        Mockito.when(repository.save(TEST_USER)).thenReturn(TEST_USER);
//        User registered = service.register(TEST_USER_DTO);
//
//        assertEquals(registered.getUsername(), TEST_USER_DTO.getUsername());
//        assertEquals(registered.getName(), TEST_USER_DTO.getName());
//        assertEquals(registered.getEmail(), TEST_USER_DTO.getEmail());
//    }
    
    /**
     * A regisztrálás során már létező felhasználó kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws UserAlredyExistsException A lehetséges kivétel
     */
    @Test(expected = UserAlredyExistsException.class)
    public void testRegisterUserAlredyExistsExceptionOne() throws Exception {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        service.register(TEST_USER_DTO);
    }

    /**
     * A regisztrálás során már létező felhasználó kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws UserAlredyExistsException A lehetséges kivétel
     */
    @Test(expected = UserAlredyExistsException.class)
    public void testRegisterUserAlredyExistsExceptionTwo() throws Exception {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(null);
        Mockito.when(repository.findByEmail(TEST_USER.getEmail())).thenReturn(TEST_USER);
        service.register(TEST_USER_DTO);
    }

    /**
     * A regisztrálás során nem létező engedély kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws AuthorityNotExistsException A lehetséges kivétel
     */
    @Test(expected = AuthorityNotExistsException.class)
    public void testRegisterAuthorityNotExistsException() throws Exception {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(null);
        Mockito.when(repository.findByEmail(TEST_USER.getEmail())).thenReturn(null);
        Mockito.when(aService.findByName(USER_ROLE.getName())).thenThrow(new AuthorityNotExistsException("EX"));
        service.register(TEST_USER_DTO);
    }

    /**
     * A frissítés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testUpdate() throws UserNotExistsException {
        Mockito.when(repository.findById(TEST_USER.getId())).thenReturn(TEST_USER);
        Mockito.when(repository.save(TEST_USER)).thenReturn(TEST_USER);
        User updated = service.update(TEST_USER.getId(), TEST_USER_DTO);

        assertEquals(updated.getUsername(), TEST_USER_DTO.getUsername());
        assertEquals(updated.getName(), TEST_USER_DTO.getName());
        assertEquals(updated.getEmail(), TEST_USER_DTO.getEmail());
    }

    /**
     * A frissítés nem létező felhasználó kivétel kiváltásának tesztelésére
     * szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testUpdateException() throws UserNotExistsException {
        Mockito.when(repository.findById(TEST_USER.getId())).thenReturn(null);
        service.update(TEST_USER.getId(), TEST_USER_DTO);
    }

    /**
     * Az e-mail cím alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByEmail() throws UserNotExistsException {
        Mockito.when(repository.findByEmail(TEST_USER.getEmail())).thenReturn(TEST_USER);
        User found = service.findByEmail(TEST_USER.getEmail());
        assertNotNull(found);
        assertEquals(TEST_USER, found);
    }

    /**
     * Az e-mail cím alapján történő keresés nem létező felhasználó kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindByEmailException() throws UserNotExistsException {
        service.findByEmail("EXCEPTION");
    }

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindById() throws UserNotExistsException {
        Mockito.when(repository.findById(TEST_USER.getId())).thenReturn(TEST_USER);
        User found = service.findById(TEST_USER.getId());
        assertEquals(TEST_USER, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező felhasználó kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindByIdException() throws UserNotExistsException {
        service.findById(1234);
    }

    /**
     * A felhasználónév alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testFindByUsername() throws Exception {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        User found = service.findByUsername(TEST_USER.getUsername());
        assertNotNull(found);
        assertEquals(TEST_USER, found);
    }

    /**
     * A felhasználónév alapján történő keresés nem létező felhasználó kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindByUsernameException() throws Exception {
        service.findByUsername("EXCEPTION");
    }

    /**
     * A felhasználónév alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testDeleteByUsername() throws UserNotExistsException {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        Mockito.doNothing().when(repository).deleteByUsername(TEST_USER.getUsername());
        service.deleteByUsername(TEST_USER.getUsername());
    }

    /**
     * A felhasználónév alapján történő törlés nem létező felhasznéló kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test(expected = UserNotExistsException.class)
    public void testDeleteByUsernameException() throws UserNotExistsException {
        service.deleteByUsername("EXCEPTION");
    }

    /**
     * A felhasználók lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_USER));
        List<User> found = service.findAll();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER, found.get(0));
    }

    /**
     * A felhasználók név alapján történő lekérdezésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testFindByName() {
        Mockito.when(repository.findByName(TEST_USER.getName())).thenReturn(Arrays.asList(TEST_USER));
        List<User> found = service.findByName(TEST_USER.getName());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER, found.get(0));
    }

    /**
     * A felhasználók neveinek lekérdezésének tesztelésére szolgáló függvény
     */
    @Test
    public void testGetNames() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(TEST_USER));
        List<String> found = service.getNames();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_USER.getName(), found.get(0));
    }

    /**
     * A felhasználónévhez tartozó személy nevének lekérdezését tesztelő
     * függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testGetName() throws UserNotExistsException {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        Map<String, String> name = service.getName(TEST_USER.getUsername());
        assertEquals(TEST_USER.getName(), name.get("name"));
    }

    /**
     * A felhasználónévhez tartozó személy nevének nem lekérdezése nem létező
     * felhasználó kivétel kiváltását tesztelő függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel
     */
    @Test
    public void testGetNameException() throws UserNotExistsException {
        Mockito.when(repository.findByUsername(TEST_USER.getUsername())).thenReturn(TEST_USER);
        service.getName(TEST_USER.getUsername());
    }

    /**
     * Az azonosító alapján történő létezés ellenőrzésének tesztelésére szolgáló
     * függvény
     */
    @Test
    public void testExistsById() {
        Mockito.when(repository.existsById(TEST_USER.getId())).thenReturn(true);
        boolean exists = service.existsById(TEST_USER.getId());
        assertTrue(exists);

        Mockito.when(repository.existsById(TEST_USER.getId())).thenReturn(false);
        exists = service.existsById(TEST_USER.getId());
        assertFalse(exists);
    }

    /**
     * A felhasználónév alapján történő létezés ellenőrzésének tesztelésére
     * szolgáló függvény
     */
    @Test
    public void testExistsByCode() {
        Mockito.when(repository.existsByUsername(TEST_USER.getUsername())).thenReturn(true);
        boolean exists = service.existsByUsername(TEST_USER.getUsername());
        assertTrue(exists);

        Mockito.when(repository.existsByUsername(TEST_USER.getUsername())).thenReturn(false);
        exists = service.existsByUsername(TEST_USER.getUsername());
        assertFalse(exists);
    }

    /**
     * Az e-mail cím alapján történő létezés ellenőrzésének tesztelésére
     * szolgáló függvény
     */
    @Test
    public void testExistsByEmail() {
        Mockito.when(repository.existsByEmail(TEST_USER.getEmail())).thenReturn(true);
        boolean exists = service.existsByEmail(TEST_USER.getEmail());
        assertTrue(exists);

        Mockito.when(repository.existsByEmail(TEST_USER.getEmail())).thenReturn(false);
        exists = service.existsByEmail(TEST_USER.getEmail());
        assertFalse(exists);
    }
}
