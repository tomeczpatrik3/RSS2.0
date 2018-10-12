package RoomReservationSystem.repository;

import RoomReservationSystem.model.Authority;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az engedélyekért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findById(int id);

    Authority findByName(String name);
    
    boolean existsById(int id);
    
    boolean existsByName(String name);

    @Transactional
    void removeByName(String name);
}
