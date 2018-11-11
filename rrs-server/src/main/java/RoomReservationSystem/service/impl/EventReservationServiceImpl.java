package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.EventReservationDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.EventReservationAlredyExistsException;
import RoomReservationSystem.exception.EventReservationNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.EventReservation;
import static RoomReservationSystem.model.EventReservation.toEventReservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.repository.EventReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.EventReservationService;
import RoomReservationSystem.service.ReservationDateService;
import static RoomReservationSystem.util.DateUtils.getTimestamp;
import java.util.ArrayList;
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
    private StatusService statusService;

    /**
     * A foglalás mentését megvalósító függvény
     *
     * @param eventReservationDTO Az eseményre vonatkozó foglalás
     * @return A mentett foglalás
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     * @throws EventReservationAlredyExistsException A lehetséges kivétel, ha a
     * foglalás már létezik
     */
    @Override
    public EventReservation save(EventReservationDTO eventReservationDTO)
            throws UserNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException, EventReservationAlredyExistsException {
        if (existsByName(eventReservationDTO.getName())) {
            throw new EventReservationAlredyExistsException(eventReservationDTO.getName());
        } else {
            EventReservation reservation = repository.save(
                    toEventReservation(
                            userService.getAuthenticatedUser(), /*A foglaláshoz tartozó felhasználó*/
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

            ReservationDate saved = reservationDateService.save(rDate);

            reservation.setDateList(Arrays.asList(saved));

            return reservation;
        }
    }

    /**
     * A foglalás azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param eventReservationDTO A foglalás
     * @return A frissített foglalás
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public EventReservation update(int id, EventReservationDTO eventReservationDTO)
            throws EventReservationNotExistsException, UserNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException {
        if (repository.findById(id) == null) {
            throw new EventReservationNotExistsException(id);
        } else {
            EventReservation converted = toEventReservation(
                    userService.findByUsername(eventReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                    classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                            eventReservationDTO.getClassroom(),
                            eventReservationDTO.getBuilding()),
                    statusService.findByName(eventReservationDTO.getStatus()), /*A foglalás státusza*/
                    Collections.emptyList(),
                    eventReservationDTO.getName(), /*A foglalás neve*/
                    eventReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
            );
            converted.setId(id);

            //Az entitás mentése:
            EventReservation saved = repository.save(converted);
            //A régi dátum(ok) törlése:
            reservationDateService.deleteByReservation(saved);
            //Az új dátumok előállítása:
            ReservationDate rDate = new ReservationDate(
                    saved,
                    getTimestamp(eventReservationDTO.getStartDate()),
                    getTimestamp(eventReservationDTO.getEndDate())
            );
            //Az új dátumok mentése:
            ReservationDate savedRDate = reservationDateService.save(rDate);
            //Az új dátumok beállítása a foglalás számára:
            saved.setDateList(Arrays.asList(savedRDate));

            return saved;
        }
    }
    
    /**
     * A saját foglalás azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param eventReservationDTO A foglalás
     * @return A frissített foglalás
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public EventReservation updateOwnById(int id, EventReservationDTO eventReservationDTO)
            throws EventReservationNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException {
        if (repository.findById(id) == null || !repository.findById(id).getUser().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            throw new EventReservationNotExistsException(id);
        } else {
            EventReservation converted = toEventReservation(
                    userService.getAuthenticatedUser(), /*A foglaláshoz tartozó felhasználó*/
                    classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                            eventReservationDTO.getClassroom(),
                            eventReservationDTO.getBuilding()),
                    statusService.findByName("PENDING"), /*A foglalás státusza*/
                    Collections.emptyList(),
                    eventReservationDTO.getName(), /*A foglalás neve*/
                    eventReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
            );
            converted.setId(id);

            //Az entitás mentése:
            EventReservation saved = repository.save(converted);
            //A régi dátum(ok) törlése:
            reservationDateService.deleteByReservation(saved);
            //Az új dátumok előállítása:
            ReservationDate rDate = new ReservationDate(
                    saved,
                    getTimestamp(eventReservationDTO.getStartDate()),
                    getTimestamp(eventReservationDTO.getEndDate())
            );
            //Az új dátumok mentése:
            ReservationDate savedRDate = reservationDateService.save(rDate);
            //Az új dátumok beállítása a foglalás számára:
            saved.setDateList(Arrays.asList(savedRDate));

            return saved;
        }
    }


    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     *
     * @param id A foglalás id-je
     * @return A foglalás
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public EventReservation findById(int id) throws EventReservationNotExistsException {
        EventReservation found = repository.findById(id);
        if (found == null) {
            throw new EventReservationNotExistsException(id);
        } else {
            return found;
        }
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
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public List<EventReservation> findByUsername(String username) throws UserNotExistsException {
        User user = userService.findByUsername(username);
        return repository.findByUser(user);

    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     *
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     */
    @Override
    public List<EventReservation> findByStatus(String statusName) throws StatusNotExistsException {
        Status status = statusService.findByName(statusName);
        if (status != null) {
            return repository.findByStatus(status);
        } else {
            throw new StatusNotExistsException(statusName);
        }
    }

    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     *
     * @param id A foglalás id-ja
     * @param statusName Az új státusz
     * @return A megváltoztatott státusszal rendelkező foglalás
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public EventReservation setStatus(int id, String statusName) throws StatusNotExistsException, EventReservationNotExistsException {
        EventReservation res = repository.findById(id);
        Status status = statusService.findByName(statusName);
        if (res != null) {
            if (status != null) {
                res.setStatus(status);
                return repository.save(res);
            } else {
                throw new StatusNotExistsException(statusName);
            }
        } else {
            throw new EventReservationNotExistsException(id);
        }
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @return A foglalások egy listában
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
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
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public EventReservation findByName(String name) throws EventReservationNotExistsException {
        EventReservation res = repository.findByName(name);
        if (res != null) {
            return res;
        } else {
            throw new EventReservationNotExistsException(name);
        }
    }

    /**
     * Egy adott azonosítóhoz tartozó foglalás törlése
     *
     * @param id Az azonosító
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public void deleteById(int id) throws EventReservationNotExistsException {
        if (repository.findById(id) != null) {
            repository.deleteById(id);
        } else {
            throw new EventReservationNotExistsException(id);
        }
    }

    /**
     * Egy adott felhasználóhoz tartozó foglalások törlése
     *
     * @param username A felhasználónév
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
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
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public void deleteByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException {
        repository.deleteByClassroom(classroomService.findByNameAndBuildingName(classroom, building));
    }

    /**
     * Egy adott státuszhoz tartozó foglalások törlése
     *
     * @param status A státusz
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     */
    @Override
    public void deleteByStatus(String status) throws StatusNotExistsException {
        repository.deleteByStatus(statusService.findByName(status));
    }

    /**
     * Egy adott névhez tartozó foglalások törlése
     *
     * @param name A név
     * @throws EventReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public void deleteByName(String name) throws EventReservationNotExistsException {
        if (repository.findByName(name) != null) {
            repository.deleteByName(name);
        } else {
            throw new EventReservationNotExistsException(name);
        }
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott azonosítóhoz
     * tartozó entitás
     *
     * @param id Az azonosító
     * @return Igen, ha létezik az azonosítóhoz tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsById(int id) {
        return repository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott névhez tartozó
     * entitás
     *
     * @param name Az esemény neve
     * @return Igen, ha létezik a névhez tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    /**
     * A foglalások neveinek lekérdezését megvalósító függvény
     *
     * @return Az foglalások nevei egy listában
     */
    @Override
    public List<String> getNames() {
        List<EventReservation> reservations = repository.findAll();
        List<String> names = new ArrayList<>();

        reservations.forEach((reservation) -> {
            names.add(reservation.getName());
        });

        return names;
    }
}
