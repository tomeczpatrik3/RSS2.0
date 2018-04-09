package RoomReservationSystem.repository;

import RoomReservationSystem.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A foglalás típusokért felelős repó
 * @author Tomecz Patrik
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findByName(String name);
    void deleteByName(String name);
}
