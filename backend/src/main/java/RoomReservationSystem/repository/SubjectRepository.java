package RoomReservationSystem.repository;

import RoomReservationSystem.model.Subject;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Az tantárgyakért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findById(int id);

    List<Subject> findByName(String name);

    Subject findByCode(String code);
    
    boolean existsById(int id);
    
    boolean existsByCode(String code);

    @Transactional
    void deleteByCode(String code);
}
