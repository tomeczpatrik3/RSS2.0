package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.MessageDTO;
import RoomReservationSystem.exception.MessageNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Message;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.MessageRepository;
import RoomReservationSystem.service.UserService;
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
 * Az üzenetekhez tartozó szervíz osztály tesztesetei
 *
 * @author Tomecz Patrik
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

    @InjectMocks
    MessageServiceImpl service;

    @Mock
    MessageRepository repository;

    @Mock
    UserService uService;

    private static final User TEST_USER_1 = new User(
            "tesztJani",
            "itsJustATest",
            "Teszt János",
            "tesztJani@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            1
    );

    private static final User TEST_USER_2 = new User(
            "tesztGabi",
            "itsJustATest",
            "Teszt Gábor",
            "tesztGabi@test.com",
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            2
    );

    private static final Message TEST_MESSAGE_1 = new Message(
            TEST_USER_1,
            TEST_USER_2,
            "Ez egy teszt üzenet Janitól",
            false,
            1
    );

    private static final Message TEST_MESSAGE_2 = new Message(
            TEST_USER_2,
            TEST_USER_1,
            "Ez egy teszt üzenet Gabitól",
            true,
            2
    );

    private static final MessageDTO TEST_MESSAGE_DTO = new MessageDTO(
            "tesztJani",
            "tesztGabi",
            "Ez egy teszt üzenet Janitól",
            true,
            1
    );

    /**
     * Az azonosító alapján történő keresés tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test
    public void testFindOwnById() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_2);
        Message found = service.findOwnById(TEST_MESSAGE_1.getId());
        assertEquals(TEST_MESSAGE_1, found);
    }

    /**
     * Az azonosító alapján történő keresés nem létező üzenet kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testFindOwnByIdExceptionOne() throws MessageNotExistsException {
        service.findOwnById(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő keresés nem létező üzenet kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testFindOwnByIdExceptionTwo() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_1);
        service.findOwnById(TEST_MESSAGE_1.getId());
    }

    /**
     * A bejelentkezett felhasználóhoz tartozó üzenetek lekérdezésének
     * tesztelésére szolgáló függvény
     */
    @Test
    public void testFindOwnMessages() {
        Mockito.when(repository.findByRecipient(TEST_USER_1)).thenReturn(Arrays.asList(TEST_MESSAGE_2));
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_1);
        List<Message> found = service.findOwnMessages();
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_MESSAGE_2, found.get(0));
    }

    /**
     * Az üzenetek küldésének tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test
    public void testSendMessage() throws UserNotExistsException {
        Mockito.when(uService.findByUsername(TEST_MESSAGE_DTO.getSender())).thenReturn(TEST_USER_1);
        Mockito.when(uService.findByUsername(TEST_MESSAGE_DTO.getRecipient())).thenReturn(TEST_USER_2);
        Mockito.when(repository.save(
                new Message(TEST_USER_1, TEST_USER_2, TEST_MESSAGE_DTO.getMessage()))
        ).thenReturn(TEST_MESSAGE_1);
        Message sent = service.sendMessage(TEST_MESSAGE_DTO);

        assertNotNull(sent);
        assertEquals(TEST_USER_1, sent.getSender());
        assertEquals(TEST_USER_2, sent.getRecipient());
        assertEquals(TEST_MESSAGE_DTO.getMessage(), sent.getMessage());
    }

    /**
     * Az üzenetek küldésé közben nem létező felhasználó kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test(expected = UserNotExistsException.class)
    public void testSendMessageExceptionOne() throws UserNotExistsException {
        Mockito.when(uService.findByUsername(TEST_MESSAGE_DTO.getSender())).thenThrow(new UserNotExistsException("EX"));
        service.sendMessage(TEST_MESSAGE_DTO);
    }

    /**
     * Az üzenetek küldésé közben nem létező felhasználó kivétel kiváltásának
     * tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test(expected = UserNotExistsException.class)
    public void testSendMessageExceptionTwo() throws UserNotExistsException {
        Mockito.when(uService.findByUsername(TEST_MESSAGE_DTO.getSender())).thenReturn(TEST_USER_1);
        Mockito.when(uService.findByUsername(TEST_MESSAGE_DTO.getRecipient())).thenThrow(new UserNotExistsException("EX"));
        service.sendMessage(TEST_MESSAGE_DTO);
    }

    /**
     * Egy adott felhasználó által küldött üzenetek lekérdezésének tesztelésére
     * szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test
    public void testFindBySender() throws UserNotExistsException {
        Mockito.when(repository.findBySender(TEST_USER_1)).thenReturn(Arrays.asList(TEST_MESSAGE_1));
        Mockito.when(uService.findByUsername(TEST_USER_1.getUsername())).thenReturn(TEST_USER_1);

        List<Message> found = service.findBySender(TEST_USER_1.getUsername());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_MESSAGE_1, found.get(0));
    }

    /**
     * Egy adott felhasználó által küldött üzenetek nem létező felhasználó
     * kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindBySenderException() throws UserNotExistsException {
        Mockito.when(uService.findByUsername(TEST_USER_1.getUsername())).thenThrow(new UserNotExistsException("EX"));
        service.findBySender(TEST_USER_1.getUsername());
    }

    /**
     * Egy adott felhasználónak küldött üzenetek lekérdezésének tesztelésére
     * szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test
    public void testFindByRecipient() throws UserNotExistsException {
        Mockito.when(repository.findByRecipient(TEST_USER_2)).thenReturn(Arrays.asList(TEST_MESSAGE_1));
        Mockito.when(uService.findByUsername(TEST_USER_2.getUsername())).thenReturn(TEST_USER_2);

        List<Message> found = service.findByRecipient(TEST_USER_2.getUsername());
        assertNotNull(found);
        assertThat(found.size(), is(1));
        assertEquals(TEST_MESSAGE_1, found.get(0));
    }

    /**
     * Egy adott felhasználónak küldött üzenetek nem létező felhasználó kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Test(expected = UserNotExistsException.class)
    public void testFindByRecipientException() throws UserNotExistsException {
        Mockito.when(uService.findByUsername(TEST_USER_2.getUsername())).thenThrow(new UserNotExistsException("EX"));
        service.findBySender(TEST_USER_2.getUsername());
    }

    /**
     * Az azonosító alapján történő "olvasott" státusz beállításának
     * tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test
    public void testMarkOwnAsRead() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_2);
        Mockito.when(repository.save(TEST_MESSAGE_1)).thenReturn(TEST_MESSAGE_1);
        service.markOwnAsRead(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő "olvasott" státusz beállítása közben nem
     * létező üzenet kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testMarkOwnAsReadExceptionOne() throws MessageNotExistsException {
        service.markOwnAsRead(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő "olvasott" státusz beállítása közben nem
     * létező üzenet kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testMarkOwnAsReadExceptionTwo() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_1);
        service.markOwnAsRead(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő "nem olvasott" státusz beállításának
     * tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test
    public void testMarkOwnAsUnread() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_2);
        Mockito.when(repository.save(TEST_MESSAGE_1)).thenReturn(TEST_MESSAGE_1);
        service.markOwnAsUnread(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő "nem olvasott" státusz beállítása közben nem
     * létező üzenet kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testMarkOwnAsUnreadExceptionOne() throws MessageNotExistsException {
        service.markOwnAsUnread(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő "nem olvasott" státusz beállítása közben nem
     * létező üzenet kivétel kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testMarkOwnAsUnreadExceptionTwo() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_1);
        service.markOwnAsUnread(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő törlés tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test
    public void testDeleteOwnById() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_2);
        Mockito.doNothing().when(repository).deleteById(TEST_MESSAGE_1.getId());
        service.deleteOwnById(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő türlés nem létező üzenet kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testDeleteOwnByIdExceptionOne() throws MessageNotExistsException {
        service.deleteOwnById(TEST_MESSAGE_1.getId());
    }

    /**
     * Az azonosító alapján történő törlés nem létező üzenet kivétel
     * kiváltásának tesztelésére szolgáló függvény
     *
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Test(expected = MessageNotExistsException.class)
    public void testDeleteOwnByIdExceptionTwo() throws MessageNotExistsException {
        Mockito.when(repository.findById(TEST_MESSAGE_1.getId())).thenReturn(TEST_MESSAGE_1);
        Mockito.when(uService.getAuthenticatedUser()).thenReturn(TEST_USER_1);
        service.deleteOwnById(TEST_MESSAGE_1.getId());
    }
}
