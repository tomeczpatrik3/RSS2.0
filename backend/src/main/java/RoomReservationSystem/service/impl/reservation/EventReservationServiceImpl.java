package RoomReservationSystem.service.impl.reservation;

import RoomReservationSystem.dto.reservation.EventReservationDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.EventReservationNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
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
import RoomReservationSystem.service.reservation.ReservationDateService;
import static RoomReservationSystem.util.DateUtils.getTimestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az eseményekre vonatkozó foglalásokkal kapcsolatos műveletekért felelős
 * osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class EventReservationServiceImpl implements EventReservationService {

    @Autowired
    private EventReservationRepository repository;

    @Autowired
    private ReservationDateService reservationDateService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StatusService statusService;

    /**
     * A foglalás mentését megvalósító függvény
     *
     * @param eventReservationDTO Az eseményre vonatkozó foglalás
     * @return A mentett foglalás
     * @throws RoomReservationSystem.exception.UserNotExistsException
     * @throws RoomReservationSystem.exception.ClassroomNotExistsException
     * @throws RoomReservationSystem.exception.StatusNotExistsException
     * @throws RoomReservationSystem.exception.SemesterNotExistsException
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     */
    @Override
    public EventReservation save(EventReservationDTO eventReservationDTO)
            throws UserNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException {
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
                getTimestamp(eventReservationDTO.getStartDate()),
                getTimestamp(eventReservationDTO.getEndDate())
        );

        reservationDateService.save(rDate);

        reservation.setDateList(Arrays.asList(rDate));

        return reservation;
    }

    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     *
     * @param id A foglalás id-je
     * @return A foglalás ha létezik, null egyébként
     */
    @Override
    public EventReservation findById(int id) {
        return repository.findById(id);
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     *
     * @return A foglalások egy listában
     */
    @Override
    public List<EventReservation> getAll() {
        return repository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     *
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public List<EventReservation> findByUsername(String username) throws UserNotExistsException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return repository.findByUser(user);
        } else {
            throw new UserNotExistsException(String.format("Ilyen felhasználónévvel (%s) rendelkező felhasználó nem létezik!", username));
        }

    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     *
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     * @throws RoomReservationSystem.exception.StatusNotExistsException
     */
    @Override
    public List<EventReservation> findByStatus(String statusName) throws StatusNotExistsException {
        Status status = statusService.findByName(statusName);
        if (status != null) {
            return repository.findByStatus(status);
        } else {
            throw new StatusNotExistsException(String.format("Ilyen névvel (%s) rendelkező státusz nem létezik!", statusName));
        }
    }

    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     *
     * @param id A foglalás id-ja
     * @param statusName Az új státusz
     * @return A megváltoztatott státusszal rendelkező foglalás
     * @throws RoomReservationSystem.exception.StatusNotExistsException
     * @throws
     * RoomReservationSystem.exception.EventReservationNotExistsException
     */
    @Override
    public EventReservation setStatus(int id, String statusName) throws StatusNotExistsException, EventReservationNotExistsException {
        EventReservation res = repository.findById(id);
        Status status = statusService.findByName(statusName);
        if (res != null) {
            if (status != null) {
                res.setStatus(status);
                return res;
            } else {
                throw new StatusNotExistsException(String.format("Ilyen névvel (%s) rendelkező státusz nem létezik", statusName));
            }
        } else {
            throw new EventReservationNotExistsException(String.format("Ilyen azonosítóval (%d) rendelkező foglalás nem létezik", id));
        }
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     *
     * @param building
     * @param classroom A tanterem
     * @return A foglalások egy listában
     * @throws RoomReservationSystem.exception.ClassroomNotExistsException
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     */
    @Override
    public List<EventReservation> findByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException {
        return repository.findByClassroom(classroomService.findByNameAndBuildingName(classroom, building));
    }

    /**
     * A foglalások név alapján történő kiválasztását megvalósító függvény
     *
     * @param name A foglalás neve
     * @return A foglalások egy listában
     * @throws
     * RoomReservationSystem.exception.EventReservationNotExistsException
     */
    @Override
    public EventReservation findByName(String name) throws EventReservationNotExistsException {
        EventReservation res = repository.findByName(name);
        if (res != null) {
            return res;
        } else {
            throw new EventReservationNotExistsException(String.format("Ilyen névvel (%s) rendelkező foglalás nem létezik", name));
        }
    }

    /**
     * Egy adott felhasználóhoz tartozó foglalások törlése
     *
     * @param username A felhasználónév
     * @throws UserNotExistsException
     */
    @Override
    public void deleteByUsername(String username) throws UserNotExistsException {
        repository.deleteByUser(userService.findByUsername(username));
    }

    /**
     * Egy adott épület adott tanterméhez tartozó foglalások törlése
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @throws ClassroomNotExistsException
     * @throws BuildingNotExistsException
     */
    @Override
    public void deleteByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException {
        repository.deleteByClassroom(classroomService.findByNameAndBuildingName(classroom, building));
    }

    /**
     * Egy adott státuszhoz tartozó foglalások törlése
     *
     * @param status A státusz
     * @throws StatusNotExistsException
     */
    @Override
    public void deleteByStatus(String status) throws StatusNotExistsException {
        repository.deleteByStatus(statusService.findByName(status));
    }

    /**
     * Egy adott névhez tartozó foglalások törlése
     *
     * @param name A név
     * @throws EventReservationNotExistsException
     */
    @Override
    public void deleteByName(String name) throws EventReservationNotExistsException {
        if (repository.findByName(name) != null) {
            repository.deleteByName(name);
        } else {
            throw new EventReservationNotExistsException(String.format("Ilyen névvel (%s) rendelkező foglalás nem létezik", name));
        }
    }
}
