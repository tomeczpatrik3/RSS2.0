package RoomReservationSystem.service;

import RoomReservationSystem.dto.MessageDTO;
import RoomReservationSystem.enums.MessageType;
import RoomReservationSystem.exception.MessageNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Message;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Az üzenetekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface MessageService {

    Message findOwnById(int id) throws MessageNotExistsException;
    
    Message sendMessage(MessageDTO messageDTO) throws UserNotExistsException;
    
    Message generateSystemMessage(String recipient, int reservationId, MessageType type) throws UserNotExistsException;

    List<Message> findBySender(String sender) throws UserNotExistsException;

    List<Message> findByRecipient(String recipient) throws UserNotExistsException;
    
    void markOwnAsRead(int id) throws MessageNotExistsException;
    
    void markOwnAsUnread(int id) throws MessageNotExistsException;

    @Transactional
    void deleteOwnById(int id) throws MessageNotExistsException;
}
