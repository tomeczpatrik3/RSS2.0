package RoomReservationSystem.repository;

import RoomReservationSystem.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A felhasználókért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByName(String name);

    @Transactional
    void deleteByUsername(String username);
}
