package RoomReservationSystem.service;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.exception.InvalidParameterException;
import RoomReservationSystem.model.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * A felhasználókkal kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface UserService extends UserDetailsService {
    void deleteByUsername(String username);
    void delete(User user);
    User register(UserDTO user);
    User findByEmail(String email);
    User findByUsername(String username) throws UsernameNotFoundException;
    User findById(int id);
    List<User> findAll();
    List<User> findByName(String name);
    List<String> getNames();
    User findByDTO(UserDTO userDTO);
}
