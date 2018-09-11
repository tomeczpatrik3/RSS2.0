package RoomReservationSystem.repository;

import RoomReservationSystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A státuszokért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findById(int id);

    Status findByName(String name);
}
