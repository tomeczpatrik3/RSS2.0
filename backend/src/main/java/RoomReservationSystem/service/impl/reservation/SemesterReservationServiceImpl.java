package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.SemesterReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SemesterReservation;
import static RoomReservationSystem.model.reservation.SemesterReservation.toSemesterReservation;
import RoomReservationSystem.repository.reservation.SemesterReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.reservation.SemesterReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A szemeszterekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class SemesterReservationServiceImpl implements SemesterReservationService {
    
    @Autowired
    private SemesterReservationRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StatusService statusService;
    
    @Autowired
    private SemesterService semesterService;
    
    /**
     * A foglalások törlését megvalósító függvény
     * @param reservation A foglalás amit törölni szeretnénk
     */
    @Override
    public void delete(SemesterReservation reservation) {
        repository.delete(reservation);
    }

     /**
     * A foglalás mentését megvalósító függvény
     * @param semesterReservationDTO A szemeszterre vonatkozó foglalás
     * @return A mentett foglalás
     */
    @Override
    public SemesterReservation save(SemesterReservationDTO semesterReservationDTO) {
        SemesterReservation reservation = repository.save(
                toSemesterReservation(
                        userService.findByUsername(semesterReservationDTO.getUser().getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        subjectService.findByCode(semesterReservationDTO.getSubject().getCode()), /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                semesterReservationDTO.getClassroom().getName(),
                                semesterReservationDTO.getBuilding().getName()),
                        semesterService.findByName(semesterReservationDTO.getSemester().getName()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        semesterReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
                )
        );
        
        return reservation;
    }

    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     * @param id A foglalás id-je
     * @return A foglalás ha létezik, null egyébként
     */
    @Override
    public SemesterReservation findById(int id) {
        return repository.findById(id);
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     * @return A foglalások egy listában
     */
    @Override
    public List<SemesterReservation> getAll() {
        return repository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     */
    @Override
    public List<SemesterReservation> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return repository.findByUser(user);
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     */
    @Override
    public List<SemesterReservation> findByStatus(String statusName) {
        Status status = statusService.findByName(statusName);
        return repository.findByStatus(status);
    }

    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     * @param id A foglalás id-ja
     * @param statusName Az új státusz
     * @return A megváltoztatott státusszal rendelkező foglalás
     */
    @Override
    public SemesterReservation setStatus(int id, String statusName) {
        SemesterReservation res = repository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return res;
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     * @param classroom A tanterem
     * @return A foglalások egy listában
     */
    @Override
    public List<SemesterReservation> findByClassroom(Classroom classroom) {
        return repository.findByClassroom(classroom);
    }

    /**
     * A foglalások tantárgy alapján történő kiválasztását megvalósító függvény
     * @param subject A tantárgy
     * @return A foglalások egy listában
     */
    @Override
    public List<SemesterReservation> findBySubject(Subject subject) {
        return repository.findBySubject(subject);
    }
    
    /**
     * A foglalások szemeszter alapján történő kiválasztását megvalósító függvény
     * @param semester A szemeszter
     * @return A foglalások egy listában
     */    
    @Override
    public List<SemesterReservation> findBySemester(String semester) {
        return repository.findBySemester(semesterService.findByName(semester));
    }

    /**
     * A foglalások egy adott dátum alapján történő kiválasztását megvalósító
     * függvény
     *
     * @param date A dátum
     * @return A foglalások egy listában
     */
//    @Override
//    public List<SimpleReservation> findByDate(Date date) {
//        return reservationDateService.findByDate(date);
//    }

    /**
     * A foglalások egy adott tanterem és adott dátum alapján történő
     * kiválasztását megvalósító függvény
     *
     * @param building Az épület amiben a tanterem található
     * @param classroom A tanterem
     * @param date A dátum
     * @return A foglalások egy listában
     */
//    @Override
//    public List<SimpleReservation> findByClassroomAndDate(String building, String classroom, Date date) {
//        List<SimpleReservation> foundByClassroom = findByClassroom(
//                classroomService.findByNameAndBuildingName(classroom, building)
//        );
//        List<SimpleReservation> foundByDate = findByDate(date);
//        return intersect(foundByClassroom, foundByDate);
//    }
}
