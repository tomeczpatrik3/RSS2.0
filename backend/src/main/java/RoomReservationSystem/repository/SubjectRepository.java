package RoomReservationSystem.repository;

import RoomReservationSystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findById(int id);
    Subject findByName(String name);
    void deleteByName(String name);
}