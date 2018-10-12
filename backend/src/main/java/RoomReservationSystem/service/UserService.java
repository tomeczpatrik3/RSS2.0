package RoomReservationSystem.service;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.exception.UserAlredyExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * A felhasználókkal kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface UserService extends UserDetailsService {

    User register(UserDTO user) throws UserAlredyExistsException, AuthorityNotExistsException, AuthorityAlredyExistsException;

    User findByEmail(String email) throws UserNotExistsException;

    User findByUsername(String username) throws UsernameNotFoundException, UserNotExistsException;

    User findById(int id) throws UserNotExistsException;

    List<User> findAll();

    List<User> findByName(String name);

    List<String> getNames();

    User findByDTO(UserDTO userDTO);

    void deleteByUsername(String username) throws UserNotExistsException;

    boolean existsById(int id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
