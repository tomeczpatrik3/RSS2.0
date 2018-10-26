package RoomReservationSystem.service.reservation;

import RoomReservationSystem.dto.reservation.ClassReservationDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassReservationNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.reservation.ClassReservation;
import java.util.List;

/**
 * A tanórákra vonatkozó foglalásokkal kapcsolatos műveletekért felelős
 * interfész Részletes információ a függvényekről a megválósításnál
 *
 * @author SimpleReservationomecz Patrik
 */
public interface ClassReservationService {

    ClassReservation save(ClassReservationDTO reservation) throws UserNotExistsException, SubjectNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException;

    ClassReservation findById(int id) throws ClassReservationNotExistsException;

    ClassReservation setStatus(int id, String status) throws StatusNotExistsException, ClassReservationNotExistsException;

    ClassReservation update(int id, ClassReservationDTO classReservationDTO) throws ClassReservationNotExistsException, UserNotExistsException, SubjectNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException;

    List<ClassReservation> getAll();

    List<ClassReservation> findByUsername(String username) throws UserNotExistsException;

    List<ClassReservation> findByStatus(String statusName) throws StatusNotExistsException;

    List<ClassReservation> findByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException;

    List<ClassReservation> findBySubjectCode(String subjectCode) throws SubjectNotExistsException;

    List<ClassReservation> findBySemester(String semester) throws SemesterNotExistsException;

    boolean existsById(int id);

    void deleteByUsername(String username) throws UserNotExistsException;

    void deleteByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException;

    void deleteBySubjectCode(String subjectCode) throws SubjectNotExistsException;

    void deleteBySemester(String semester) throws SemesterNotExistsException;

    void deleteByStatus(String status) throws StatusNotExistsException;
}
