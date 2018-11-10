package RoomReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Üzenet entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "MESSAGES")
public class Message extends BaseEntity {

    /*A feladó*/
    @JsonIgnore
    @JoinColumn(name = "SENDER", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private User sender;

    /*A címzett*/
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "RECIPIENT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User recipient;

    /*Az üzenet*/
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MESSAGE")
    private String message;

    /*Az üzenet státusza*/
    @NotNull
    @Column(name = "UNREAD")
    private boolean unread;

    /**
     * Az osztály üres konstruktora
     */
    public Message() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param recipient Az üzenet címzettje
     * @param message Az üzenet
     */
    public Message(User recipient, String message) {
        super();
        this.unread = true;
        this.recipient = recipient;
        this.message = message;
    }

    /**
     * Az osztály konstruktora
     *
     * @param sender Az üzenet feladója
     * @param recipient Az üzenet címzettje
     * @param message Az üzenet
     */
    public Message(User sender, User recipient, String message) {
        this.unread = true;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }
    
    /**
     * Az osztály konstruktora
     *
     * @param sender Az üzenet feladója
     * @param recipient Az üzenet címzettje
     * @param message Az üzenet
     * @param unread Az üzenet státusza
     * @param id Az azonosító
     */
    public Message(User sender, User recipient, String message, boolean unread, int id) {
        super(id);
        this.unread = unread;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }
}
