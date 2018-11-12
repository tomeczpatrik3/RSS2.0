package RoomReservationSystem.validation;


import static RoomReservationSystem.constants.ErrorMessageConstants.*;
import RoomReservationSystem.dto.MessageDTO;
import RoomReservationSystem.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Az üzenetek adatainak ellenőrzését végző osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class MessageValidator implements Validator {

    @Autowired
    private MessageService messageService;

    /**
     * A függvény amely az objektum típusát
     *
     * @param clazz Az objektum
     * @return Igaz, ha az objektum megfelelő típusú, hamis egyébként
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == MessageDTO.class;
    }

    /**
     * A validálást végző függvény
     *
     * @param target Az objektum amit validálunk
     * @param errors A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "sender", "messageDTO.sender.empty", MESSAGE_SENDER_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "recipient", "messageDTO.recipient.empty", MESSAGE_RECIPIENT_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "message", "messageDTO.message.empty", MESSAGE_MESSAGE_EMPTY);

        MessageDTO messageDTO = (MessageDTO) target;

        /*A feladó felhasználónevének validálása*/
        if (messageDTO.getSender() != null && messageDTO.getSender().length() < 5
                || messageDTO.getSender().length() > 30) {
            errors.rejectValue("sender", "messageDTO.sender.size", MESSAGE_SENDER_SIZE);
        }
        
        /*A címzett felhasználónevének validálása*/
        if (messageDTO.getRecipient() != null && messageDTO.getRecipient().length() < 5
                || messageDTO.getRecipient().length() > 30) {
            errors.rejectValue("recipient", "messageDTO.recipient.size", MESSAGE_RECIPIENT_SIZE);
        }

        /*Az üzenet szövegének validálása*/
        if (messageDTO.getMessage() != null && messageDTO.getMessage().length() < 1 || messageDTO.getMessage().length() > 255) {
            errors.rejectValue("message", "messageDTO.message.size", MESSAGE_MESSAGE_SIZE);
        }
    }
}
