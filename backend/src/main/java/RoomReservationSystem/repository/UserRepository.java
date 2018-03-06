package RoomReservationSystem.repository;

import RoomReservationSystem.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByName(String name);
    void deleteByUsername(String username);
    User findByEmail(String email);
}