package RoomReservationSystem.repository;

import RoomReservationSystem.model.Subject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findById(int id);
    List<Subject> findByName(String name);
    Subject findByCode(String code);
    void deleteByCode(String code);
}