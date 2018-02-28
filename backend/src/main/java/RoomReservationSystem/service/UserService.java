package RoomReservationSystem.service;

import RoomReservationSystem.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    void deleteByUsername(String username);
    void deleteAll();
    void delete(User user);
    Iterable<User> findAll();
    List<User> findByName(String name);
    List<String> getNames();
}
