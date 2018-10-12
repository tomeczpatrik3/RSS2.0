package RoomReservationSystem.repository;

import RoomReservationSystem.model.Building;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az épületekért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    Building findByName(String name);

    Building findById(int id);
    
    boolean existsById(int id);
    
    boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);
}
