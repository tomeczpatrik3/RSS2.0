package RoomReservationSystem.repository;

import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.ClassReservation;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A tanórákra vonatkozó foglalásokért felelős repó
 *
 * @author Tomecz Patrik
 */
@Repository
public interface ClassReservationRepository extends JpaRepository<ClassReservation, Integer> {

    ClassReservation findById(int id);

    List<ClassReservation> findByUser(User user);

    List<ClassReservation> findByStatus(Status status);

    List<ClassReservation> findByClassroom(Classroom classroom);

    List<ClassReservation> findBySubject(Subject subject);

    List<ClassReservation> findBySemester(Semester semester);
    
    boolean existsById(int id);

    @Transactional
    void deleteByUser(User user);

    @Transactional
    void deleteByClassroom(Classroom classroom);

    @Transactional
    void deleteBySubject(Subject subject);

    @Transactional
    void deleteBySemester(Semester semester);

    @Transactional
    void deleteByStatus(Status status);
}
