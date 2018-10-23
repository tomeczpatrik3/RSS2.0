package RoomReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Engedély entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Table(name = "AUTHORITIES")
public class Authority extends BaseEntity {

    /*Az engedély neve*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME", unique = true)
    private String name;

    /*Az engedélyhez rendelt felhasználók listája*/
    @JsonIgnore
    @JoinTable(name = "users_authorities", joinColumns = {
        @JoinColumn(name = "authority_id", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "ID")})
    @ManyToMany
    private List<User> userList;

    /**
     * Az osztály üres konstruktora
     */
    public Authority() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param name Az engedély neve
     * @param userList A felhasználók, akik rendelkeznek az engedéllyel
     * @param id Az engedély azonosítója
     */
    public Authority(String name, List<User> userList, Integer id) {
        super(id);
        this.name = name;
        this.userList = userList;
    }

    /**
     * Felhasználó hozzáadása az adott engedélyhez
     *
     * @param user A felhasználó
     */
    public void addUser(User user) {
        userList.add(user);
    }

    /**
     * Engedély megvonása a felhasználótól
     *
     * @param user A felhasználó
     */
    public void removeUser(User user) {
        userList.remove(user);
    }
}
