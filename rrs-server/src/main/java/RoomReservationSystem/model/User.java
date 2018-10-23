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
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {

    /*Felhasználónév*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME", unique = true)
    private String username;

    /*Jelszó*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORD")
    private String password;

    /*A felhasználó neve*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;

    /*A felhasználó e-mail címe*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL", unique = true)
    private String email;

    /*A felhasználóhoz tartozó engedélyek listája*/
    @JsonIgnore
    @ManyToMany(mappedBy = "userList")
    private List<Authority> authorityList;

    /*A felhasználóhoz tartozó foglalások listája*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Reservation.class)
    private List<Reservation> reservationList;

    /**
     * Az osztály üres konstruktora
     */
    public User() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param username A felhasználónév
     * @param password A jelszó
     * @param name A felhasználó neve
     * @param email A felhasználó e-mail címe
     * @param authorityList A felhasználóhoz tartozó engedélyek listája
     * @param reservationList A felhasználóhoz tartozó foglalásom listája
     * @param id Az azonosító
     */
    public User(String username, String password, String name, String email, List<Authority> authorityList, List<Reservation> reservationList, Integer id) {
        super(id);
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.authorityList = authorityList;
        this.reservationList = reservationList;
    }

    /**
     * A felhasználóhoz tartozó engedélyek lekérdezése
     *
     * @return Az engedélyek egy Collection-ben
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorityList.forEach((authority) -> {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        });
        return authorities;
    }

    /**
     * Az isAccountNonExpired() függvény
     *
     * @return Igaz
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Az isAccountNonLocked() függvény
     *
     * @return Igaz
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Az isCredentialsNonExpired() függvény
     *
     * @return Igaz
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Az isEnabled() függvény
     *
     * @return Igaz
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * A UserDTO objektum User objektummá konvertálását végrehajtó megtódus
     *
     * @param userDTO A UserDTO objektum
     * @param encodedPassword A kódolt jelszó
     * @param authorityList Az engedélyek egy listában
     * @return A User objektum
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
