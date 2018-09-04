package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.reservation.EventReservation;
import static RoomReservationSystem.model.reservation.EventReservation.toEventReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import RoomReservationSystem.repository.reservation.EventReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.reservation.EventReservationService;
import static RoomReservationSystem.util.DateUtils.getDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az eseményekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class EventReservationServiceImpl implements EventReservationService {
    
    @Autowired
    private EventReservationRepository repository;

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
    public void delete(EventReservation reservation) {
        repository.delete(reservation);
    }

    /**
     * A foglalás mentését megvalósító függvény
     * @param eventReservationDTO Az eseményre vonatkozó foglalás
     * @return A mentett foglalás
     */
    @Override
    public EventReservation save(EventReservationDTO eventReservationDTO) {
        EventReservation reservation = repository.save(
                toEventReservation(
                        userService.findByUsername(eventReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                eventReservationDTO.getClassroom(),
                                eventReservationDTO.getBuilding()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        Collections.emptyList(),
                        eventReservationDTO.getName(), /*A foglalás neve*/
                        eventReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
                )
        );
        
        ReservationDate rDate = new ReservationDate(
                reservation, 
                getDateTime(eventReservationDTO.getStartDate()),
                getDateTime(eventReservationDTO.getEndDate())
        );
        
        reservation.setDateList(Arrays.asList(rDate));
        
        return reservation;
    }

    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     * @param id A foglalás id-je
     * @return A foglalás ha létezik, null egyébként
     */
    @Override
    public EventReservation findById(int id) {
        return repository.findById(id);
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     * @return A foglalások egy listában
     */
    @Override
    public List<EventReservation> getAll() {
        return repository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     */
    @Override
    public List<EventReservation> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return repository.findByUser(user);
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     */
    @Override
    public List<EventReservation> findByStatus(String statusName) {
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
    public EventReservation setStatus(int id, String statusName) {
        EventReservation res = repository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return res;
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     * @param classroom A tanterem
     * @return A foglalások egy listában
     */
    @Override
    public List<EventReservation> findByClassroom(Classroom classroom) {
        return repository.findByClassroom(classroom);
    }

    /**
     * A foglalások név alapján történő kiválasztását megvalósító függvény
     * @param name A foglalás neve
     * @return A foglalások egy listában
     */    
    @Override
    public EventReservation findByName(String name) {
        return repository.findByName(name);
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
