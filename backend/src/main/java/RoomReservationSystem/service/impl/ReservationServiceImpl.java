package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.EventReservationDTO;
import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.dto.SemesterReservationDTO;
import RoomReservationSystem.dto.SimpleReservationDTO;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.ReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.ReservationService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import static RoomReservationSystem.model.Reservation.toReservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.service.ReservationDateService;
import RoomReservationSystem.service.SemesterService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A foglalásokkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    UserService userService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    StatusService statusService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    ReservationDateService reservationDateService;

    /**
     * A foglalások törlését megvalósító függvény
     *
     * @param res A foglalás amit törölni szeretnénk
     */
    @Override
    public void delete(Reservation res) {
        reservationRepository.delete(res);
    }

    @Override
    public Reservation save(SimpleReservationDTO simpleReservationDTO) {
        Reservation reservation = reservationRepository.save(
                toReservation(
                        userService.findByUsername(simpleReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        semesterService.findByName(simpleReservationDTO.getSemesterName()), /*A félév*/
                        subjectService.findByCode(simpleReservationDTO.getSubjectCode()), /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                simpleReservationDTO.getRoomName(),
                                simpleReservationDTO.getBuildingName()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        simpleReservationDTO.getNote(), /*A foglaláshoz tartozó megjegyzés*/
                        simpleReservationDTO.getReservationName()
                )
        );

        reservation.setDateList(Arrays.asList(reservationDateService.save(
                reservation,
                simpleReservationDTO.getStartDateTime(),
                simpleReservationDTO.getEndDateTime()
        )));
        
        return reservation;
    }
    
    @Override
    public Reservation save(EventReservationDTO eventReservationDTO) {
        Reservation reservation = reservationRepository.save(
                toReservation(
                        userService.findByUsername(eventReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        null, /*A félév*/
                        null, /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                eventReservationDTO.getRoomName(),
                                eventReservationDTO.getBuildingName()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        eventReservationDTO.getNote(), /*A foglaláshoz tartozó megjegyzés*/
                        eventReservationDTO.getReservationName()
                )
        );
        
        reservation.setDateList(Arrays.asList(reservationDateService.save(
                reservation,
                eventReservationDTO.getStartDateTime(),
                eventReservationDTO.getEndDateTime()
        )));
        
        return reservation;        
    }
    
    //TODO: implement semester reservation
    @Override
    public Reservation save(SemesterReservationDTO semesterReservationDTO) {
        Reservation reservation = reservationRepository.save(
                toReservation(
                        userService.findByUsername(semesterReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                        semesterService.findByName(semesterReservationDTO.getSemesterName()), /*A félév*/
                        subjectService.findByCode(semesterReservationDTO.getSubjectCode()), /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                semesterReservationDTO.getRoomName(),
                                semesterReservationDTO.getBuildingName()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        semesterReservationDTO.getNote(), /*A foglaláshoz tartozó megjegyzés*/
                        semesterReservationDTO.getReservationName()
                )
        );

        reservation.setDateList(Arrays.asList(reservationDateService.save(
                reservation,
                semesterReservationDTO.getStartDateTime(),
                semesterReservationDTO.getEndDateTime()
        )));
        
        return reservation;
    }

    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     *
     * @param id A foglalás id-je
     * @return A foglalás ha létezik, null egyébként
     */
    @Override
    public Reservation findById(int id) {
        return reservationRepository.findById(id);
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     *
     * @return A foglalások egy listában
     */
    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     *
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     */
    @Override
    public List<Reservation> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return reservationRepository.findByUser(user);
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     *
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     */
    @Override
    public List<Reservation> findByStatus(String statusName) {
        Status status = statusService.findByName(statusName);
        return reservationRepository.findByStatus(status);
    }

    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     *
     * @param id A foglalás id-ja
     * @param statusName Az új státusz
     * @return A megváltoztatott státusszal rendelkező foglalás
     */
    @Override
    public Reservation setStatus(int id, String statusName) {
        Reservation res = reservationRepository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return res;
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     *
     * @param classroom A tanterem
     * @return A foglalások egy listában
     */
    @Override
    public List<Reservation> findByClassroom(Classroom classroom) {
        return reservationRepository.findByClassroom(classroom);
    }

    /**
     * A foglalások egy adott dátum alapján történő kiválasztását megvalósító
     * függvény
     *
     * @param date A dátum
     * @return A foglalások egy listában
     */
    @Override
    public List<Reservation> findByDate(Date date) {
        return reservationDateService.findByDate(date);
    }

    /**
     * A foglalások egy adott tanterem és adott dátum alapján történő
     * kiválasztását megvalósító függvény
     *
     * @param building Az épület amiben a tanterem található
     * @param classroom A tanterem
     * @param date A dátum
     * @return A foglalások egy listában
     */
    @Override
    public List<Reservation> findByClassroomAndDate(String building, String classroom, Date date) {
        List<Reservation> foundByClassroom = findByClassroom(
                classroomService.findByNameAndBuildingName(classroom, building)
        );
        List<Reservation> foundByDate = findByDate(date);
        return intersect(foundByClassroom, foundByDate);

    }

    /**
     * Két Reservation objektumokat tartalmazó lista metszetének megkeresése:
     *
     * @param listA Az első lista
     * @param listB A második lista
     * @return A metszet
     */
    private List<Reservation> intersect(List<Reservation> listA, List<Reservation> listB) {
        listA.retainAll(listB);
        return listA;
    }
}
