package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.MessageDTO;
import RoomReservationSystem.enums.MessageType;
import RoomReservationSystem.exception.MessageNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Message;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.MessageRepository;
import RoomReservationSystem.service.MessageService;
import RoomReservationSystem.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az üzenetekkel kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    /**
     * A saját üzenet azonosító alapján történő keresését lehetővé tevő függvény
     *
     * @param id Az azonosító
     * @return Az adott azonosítóval rendelkező üzenet
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Override
    public Message findOwnById(int id) throws MessageNotExistsException {
        if (messageRepository.findById(id) != null
                && messageRepository.findById(id).getRecipient().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            return messageRepository.findById(id);
        } else {
            throw new MessageNotExistsException(id);
        }
    }

    /**
     * A bejelentkezett felhasználóhoz tartozó üzenetek kilistázását lehetővé
     * tevő függvény
     *
     * @return A megfelelő üzenetek egy listában
     */
    @Override
    public List<Message> findOwnMessages() {
        User authenticatedUser = this.userService.getAuthenticatedUser();
        return messageRepository.findByRecipient(authenticatedUser);
    }

    /**
     * Az üzenet elküldését lehetővé tevő függvény
     *
     * @param messageDTO Az üzenet
     * @return Az elküldött üzenet
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public Message sendMessage(MessageDTO messageDTO) throws UserNotExistsException {
        if (messageDTO.getRecipient() == null || userService.getAuthenticatedUser() == null) {
            throw new UserNotExistsException("-");
        } else {
            return messageRepository.save(new Message(
                    userService.getAuthenticatedUser(),
                    userService.findByUsername(messageDTO.getRecipient()),
                    messageDTO.getMessage())
            );
        }
    }

    /**
     * A rendszerüzenet generálásáért felelős függvény (jelenleg foglalások
     * elfogadása, elutasítása)
     *
     * @param recipient A címzett
     * @param reservationId A foglalás azonosítója
     * @param type Az üzenet típusa
     * @return A megfelelő üzenet
     * @throws UserNotExistsException A lehetséges kivétel A lehetséges kivétel,
     * ha a felhasználó nem létezik
     */
    @Override
    public Message generateSystemMessage(String recipient, int reservationId, MessageType type) throws UserNotExistsException {
        User rec = userService.findByUsername(recipient);
        if (type.equals(MessageType.ACCEPT_MSG)) {
            return messageRepository.save(new Message(
                    null,
                    rec,
                    String.format("Az alábbi azonosítóval rendelkező foglalás ELFOGADÁSRA került: %d", reservationId)
            ));
        } else { //type.equals(MessageType.DECLINE_MSG)
            return messageRepository.save(new Message(
                    null,
                    rec,
                    String.format("Az alábbi azonosítóval rendelkező foglalás ELUTASÍTÁSRA került: %d", reservationId)
            ));
        }
    }

    /**
     * Az adott felhasználó által küldött üzenetek lekérdezését lehetővé tevő
     * függvény
     *
     * @param sender A feladó
     * @return A megfelelő üzenetek egy listában
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public List<Message> findBySender(String sender) throws UserNotExistsException {
        return messageRepository.findBySender(userService.findByUsername(sender));
    }

    /**
     * Az adott felhasználó fogadott küldött üzenetek lekérdezését lehetővé tevő
     * függvény
     *
     * @param recipient A címzett
     * @return A megfelelő üzenetek egy listában
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public List<Message> findByRecipient(String recipient) throws UserNotExistsException {
        return messageRepository.findByRecipient(userService.findByUsername(recipient));
    }

    /**
     * Egy adott (saját) üzenet státuszának "olvasott"-ra állításáért felelős
     * függvény
     *
     * @param id Az azonosító
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Override
    public void markOwnAsRead(int id) throws MessageNotExistsException {
        if (messageRepository.findById(id) != null
                && messageRepository.findById(id).getRecipient().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            Message found = messageRepository.findById(id);
            found.setUnread(false);
            messageRepository.save(found);
        } else {
            throw new MessageNotExistsException(id);
        }
    }

    /**
     * Egy adott (saját) üzenet státuszának "nem olvasott"-ra állításáért
     * felelős függvény
     *
     * @param id Az azonosító
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Override
    public void markOwnAsUnread(int id) throws MessageNotExistsException {
        if (messageRepository.findById(id) != null
                && messageRepository.findById(id).getRecipient().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            Message found = messageRepository.findById(id);
            found.setUnread(true);
            messageRepository.save(found);
        } else {
            throw new MessageNotExistsException(id);
        }
    }

    /**
     * A saját üzenet azonosító alapján történő törlését lehetővé tevő függvény
     *
     * @param id Az azonosító
     * @throws MessageNotExistsException A lehetséges kivétel, ha az üzenet nem
     * létezik
     */
    @Override
    public void deleteOwnById(int id) throws MessageNotExistsException {
        if (messageRepository.findById(id) != null
                && messageRepository.findById(id).getRecipient().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            messageRepository.deleteById(id);
        } else {
            throw new MessageNotExistsException(id);
        }
    }
}
