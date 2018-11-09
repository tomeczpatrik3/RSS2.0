package RoomReservationSystem.controller.api;

import RoomReservationSystem.dto.MessageDTO;
import static RoomReservationSystem.dto.MessageDTO.toMessageDTO;
import static RoomReservationSystem.dto.MessageDTO.toMessageDTOList;
import RoomReservationSystem.enums.MessageType;
import RoomReservationSystem.exception.MessageNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Message;
import RoomReservationSystem.service.MessageService;
import static RoomReservationSystem.util.ExceptionUtils.handleException;
import static RoomReservationSystem.util.ValidationUtils.concatErrors;
import RoomReservationSystem.validation.MessageValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Az üzenetekhez tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/message")
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageValidator messageValidator;

    /**
     * A függvény ami visszaadja az adott azonosítóhoz tartozó üzenetet
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findOwnById")
    public ResponseEntity findOwnById(@RequestParam(value = "id", required = true) int id) {
        try {
            return ResponseEntity.ok(toMessageDTO(messageService.findOwnById(id)));
        } catch (MessageNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott felhasználó által feladott üzeneteket
     *
     * @param sender A feladó
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findBySender")
    public ResponseEntity findBySender(@RequestParam(value = "sender", required = true) String sender) {
        try {
            return ResponseEntity.ok(toMessageDTOList(messageService.findBySender(sender)));
        } catch (UserNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja az adott felhasználóhoz érkezett üzeneteket
     *
     * @param recipient A címzett
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByRecipient")
    public ResponseEntity findByRecipient(@RequestParam(value = "recipient", required = true) String recipient) {
        try {
            return ResponseEntity.ok(toMessageDTOList(messageService.findByRecipient(recipient)));
        } catch (UserNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami visszaadja a saját üzeneteinket (bejelentkezett
     * felhasználó)
     *
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/findOwnMessages")
    public ResponseEntity findOwnMessages() {
        return ResponseEntity.ok(toMessageDTOList(messageService.findOwnMessages()));
    }

    /**
     * A függvény ami az üzenetek küldéséért felelős
     *
     * @param messageDTO Az üzenet
     * @param bindingResult A BindingResult objektum
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestBody MessageDTO messageDTO, BindingResult bindingResult) {
        messageValidator.validate(messageDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                Message sent = messageService.sendMessage(messageDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(toMessageDTO(sent));
            } catch (UserNotExistsException ex) {
                return handleException(ex);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }

    /**
     * A függvény ami a rendszerüzenetek generálásáért felelős
     *
     * @param recipient A címzett
     * @param reservationId A foglalás azonosítója
     * @param messageType Az generálandó üzenet típusa
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/generateSystemMessage")
    public ResponseEntity generateSystemMessage(
            @RequestParam(value = "recipient", required = true) String recipient,
            @RequestParam(value = "reservationId", required = true) int reservationId,
            @RequestParam(value = "messageType", required = true) String messageType) {
        try {
            return ResponseEntity.ok(toMessageDTO(messageService.generateSystemMessage(recipient, reservationId, MessageType.valueOf(messageType))));
        } catch (UserNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami "olvasott" státuszra állítja a megfelelő üzenetet
     *
     * @param id Az üzenet azonosítója
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/markOwnAsRead")
    public ResponseEntity markOwnAsRead(@RequestParam(value = "id", required = true) int id) {
        try {
            messageService.markOwnAsRead(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (MessageNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami "nem olvasott" státuszra állítja a megfelelő üzenetet
     *
     * @param id Az üzenet azonosítója
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/markOwnAsUnread")
    public ResponseEntity markOwnAsUnread(@RequestParam(value = "id", required = true) int id) {
        try {
            messageService.markOwnAsUnread(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (MessageNotExistsException ex) {
            return handleException(ex);
        }
    }

    /**
     * A függvény ami törli az adott azonosítóhoz tartozó üzenetet
     *
     * @param id Az azonosító
     * @return A megfelelő válasz entitás
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/deleteOwnById")
    public ResponseEntity deleteOwnById(@RequestParam(value = "id", required = true) int id) {
        try {
            messageService.deleteOwnById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (MessageNotExistsException ex) {
            return handleException(ex);
        }
    }
}
