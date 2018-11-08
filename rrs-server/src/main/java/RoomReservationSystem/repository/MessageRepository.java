package RoomReservationSystem.repository;

import RoomReservationSystem.model.Message;
import RoomReservationSystem.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az üzenetekért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findById(int id);

    List<Message> findBySender(User sender);

    List<Message> findByRecipient(User recipient);

    @Transactional
    void deleteById(int id);
}
