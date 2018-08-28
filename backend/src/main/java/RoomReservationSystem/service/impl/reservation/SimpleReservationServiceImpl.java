package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.SimpleReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.SimpleClassReservation;
import static RoomReservationSystem.model.reservation.SimpleClassReservation.toSimpleReservation;
import RoomReservationSystem.repository.reservation.SimpleReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.reservation.SimpleReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az egyszerű foglalásokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class SimpleReservationServiceImpl implements SimpleReservationService {
    
    @Autowired
    private SimpleReservationRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StatusService statusService;
    
    /**
     * A foglalások törlését megvalósító függvény
     * @param reservation A foglalás amit törölni szeretnénk
     */
    @Override
    public void delete(SimpleClassReservation reservation) {
        repository.delete(reservation);
    }

     /**
     * A foglalás mentését megvalósító függvény
     * @param simpleReservationDTO Az egyszerű foglalás
     * @return A mentett foglalás
     */
    @Override
    public SimpleClassReservation save(SimpleReservationDTO simpleReservationDTO) {
        SimpleClassReservation reservation = repository.save(
                toSimpleReservation(
                        userService.findByUsername(simpleReservationDTO.getUser().getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        subjectService.findByCode(simpleReservationDTO.getSubject().getCode()), /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                simpleReservationDTO.getClassroom().getName(),
                                simpleReservationDTO.getBuilding().getName()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        simpleReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
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
    public SimpleClassReservation findById(int id) {
        return repository.findById(id);
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     * @return A foglalások egy listában
     */
    @Override
    public List<SimpleClassReservation> getAll() {
        return repository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     */
    @Override
    public List<SimpleClassReservation> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return repository.findByUser(user);
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     */
    @Override
    public List<SimpleClassReservation> findByStatus(String statusName) {
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
    public SimpleClassReservation setStatus(int id, String statusName) {
        SimpleClassReservation res = repository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return res;
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     * @param classroom A tanterem
     * @return A foglalások egy listában
     */
    @Override
    public List<SimpleClassReservation> findByClassroom(Classroom classroom) {
        return repository.findByClassroom(classroom);
    }

    /**
     * A foglalások tantárgy alapján történő kiválasztását megvalósító függvény
     * @param subject A tantárgy
     * @return A foglalások egy listában
     */
    @Override
    public List<SimpleClassReservation> findBySubject(Subject subject) {
        return repository.findBySubject(subject);
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
