package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ReservationDTO;
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
import RoomReservationSystem.service.SemesterService;
import static RoomReservationSystem.util.DateUtils.generateReservationDate;
import static RoomReservationSystem.util.DateUtils.getDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A foglalásokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class ReservationServiceImpl implements ReservationService{
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
    

    /**
     * A foglalások törlését megvalósító függvény
     * @param   res     A foglalás amit törölni szeretnénk
     */
    @Override
    public void delete(Reservation res){
        reservationRepository.delete(res);
    }
    
    /**
     * A foglalás rögzítését megvalósító függvény
     * A ReservationDTO típusú objektumot átalakítjuk Reservation típusú objektummá
     * --> Beállítjuk a foglaláshoz tartozó tantermet, tantárgyat, felhasználót és státuszt is
     * --> A státusz alapértelmezetten "PENDING", azaz feldolgozás alatt lesz
     * 
     * @param   reservationDTO      A rögzíteni kívánt foglalás (DTO)
     * @return                      A rögzített foglalás
     */
    @Override
    public Reservation save(ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.save(
                toReservation(
                        userService.findByDTO(reservationDTO.getUser()),                    /*A foglaláshoz tartozó felhasználó*/
                        semesterService.findByName(reservationDTO.getSemester()),           /*A félév*/
                        subjectService.findByDTO(reservationDTO.getSubject()),              /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByDTO(reservationDTO.getClassroom()),          /*A foglaláshoz tartozó tanterem*/
                        statusService.findByName("PENDING"),                                /*A foglalás státusza*/
                        reservationDTO.getNote()                                            /*A foglaláshoz tartozó megjegyzés*/          
                )
        );
        
        List<ReservationDate> dates = new ArrayList<>();

        if (reservationDTO.getStatus().equals("day")) {
            dates.add(generateReservationDate(
                    reservation,
                    getDate(reservationDTO.getDates()[0]),
                    reservationDTO.getStartTime(),
                    reservationDTO.getEndTime()
            ));
        }
        else { /*semester --> féléves foglalás*/
            dates = generateReservationDates(
                    semesterService.findByName(reservationDTO.getSemester()),
                    reservation,
                    reservationDTO.getDay(),
                    reservationDTO.getStartTime(),
                    reservationDTO.getEndTime()                    
            );
        }
       
    }
    
    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     * @param   id  A foglalás id-je
     * @return      A foglalás ha létezik, null egyébként
     */
    @Override
    public Reservation findById(int id){       
        return reservationRepository.findById(id);
    }
    
    /**
     * A foglalások lekérdezését megvalósító függvény
     * @return  A foglalások egy listában
     */
    @Override
    public List<Reservation> getAll(){
        return reservationRepository.findAll();
    }
    
    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító függvény
     * @param   username    A felhasználónév
     * @return              Az adott felhasználó által történt foglalások egy listában
     */
    @Override
    public List<Reservation> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return reservationRepository.findByUser(user);
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     * @param   statusName  A keresendő státusz
     * @return              Az adott státusszal rendelkező foglalások
     */
    @Override
    public List<Reservation> findByStatus(String statusName) {
        Status status = statusService.findByName(statusName);
        return reservationRepository.findByStatus(status);
    }
    
    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     * @param id            A foglalás id-ja
     * @param statusName    Az új státusz
     * @return              A megváltoztatott státusszal rendelkező foglalás
     */
    @Override
    public Reservation setStatus(int id, String statusName) {
        Reservation res = reservationRepository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return res;
    }
}
