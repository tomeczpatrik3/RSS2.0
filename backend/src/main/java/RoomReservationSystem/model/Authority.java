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
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Engedély entitás
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity{
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", unique=true)
    private String name;    /*Az engedély neve*/           
    
    @JsonIgnore
    @JoinTable(name = "users_authorities", joinColumns = {
        @JoinColumn(name = "authority_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private List<User> userList;    /*Azon felhasználók listája akik rendelkeznek az engedéllyel*/
    
    /**
     * Felhasználó hozzáadása az adott engedélyhez
     * @param   user    A felhasználó
     */
    public void addUser(User user) {
        userList.add(user);
    }
}
