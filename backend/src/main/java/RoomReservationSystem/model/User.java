package RoomReservationSystem.model;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.dto.UserDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Felhasználó entitás
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username", unique=true)
    private String username; /*Felhasználónév*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password; /*Jelszó*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;    /*A felhasználó neve*/
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email", unique=true)
    private String email;   /*A felhasználó e-mail címe*/
    
    @JsonIgnore
    @ManyToMany(mappedBy = "userList")
    private List<Authority> authorityList; /*A felhasználóhoz tartozó engedélyek listája*/
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservationList; /*A felhasználóhoz tartozó foglalások listája*/

    /**
     * A felhasználóhoz tartozó engedélyek lekérdezése
     * @return  Az engedélyek egy Collection-ben
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority: authorityList) {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /**
     * A UserDTO objektum User objektummá konvertálását végrehajtó megtódus
     * @param   userDTO             A UserDTO objektum 
     * @param   encodedPassword     A kódolt jelszó
     * @param   authorityList       Az engedélyek egy listában
     * @return                      A User objektum
     */
    public static User toUser(UserDTO userDTO, String encodedPassword, List<Authority> authorityList) {
        return new User(
                userDTO.getUsername(),
                encodedPassword,
                userDTO.getName(),
                userDTO.getEmail(),
                authorityList,
                Collections.emptyList()
        );
    }

}
