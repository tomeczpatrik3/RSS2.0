package RoomReservationSystem.repository;

import RoomReservationSystem.model.Semester;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A félévekért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {

    Semester findByName(String name);

    @Transactional
    void deleteByName(String name);
}
