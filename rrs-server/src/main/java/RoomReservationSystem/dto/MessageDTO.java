package RoomReservationSystem.dto;

import RoomReservationSystem.model.Message;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az üzenetekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageDTO extends BaseDTO {

    /*A feladó*/
    private String sender;
    /*A címzett*/
    private String recipient;
    /*Az üzenet*/
    private String message;
    /*Az üzenet státusza*/
    private boolean unread;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param sender A feladó
     * @param recipient A címzett
     * @param message Az üzenet
     * @param unread Az üzenet státusza
     */
    public MessageDTO(
            long id,
            String sender,
            String recipient,
            String message,
            boolean unread
    ) {
        super(id);
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.unread = unread;
    }

    /**
     * Az osztály üres konstruktora
     */
    public MessageDTO() {
    }

    /**
     * A Message objektumból MessageDTO objektum létrehozásáért felelős metódus
     *
     * @param message A Message objektum
     * @return A MessageDTO objektum
     */
    public static MessageDTO toMessageDTO(Message message) {
        String from;
        if (message.getSender() != null) {
            from = message.getSender().getUsername();
        } else {
            from = "RENDSZERÜZENET";
        }

        return new MessageDTO(
                message.getId(),
                from,
                message.getRecipient().getUsername(),
                message.getMessage(),
                message.isUnread()
        );
    }

    /**
     * Több Message objektum MessageDTO objektummá alakításáért felelős metódus
     *
     * @param messages A Message objektumok egy listában
     * @return A MessageDTO objektumok egy listában
     */
    public static List<MessageDTO> toMessageDTOList(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<>();
        messages.forEach((message) -> {
            messageDTOs.add(toMessageDTO(message));
        });
        return messageDTOs;
    }
}
