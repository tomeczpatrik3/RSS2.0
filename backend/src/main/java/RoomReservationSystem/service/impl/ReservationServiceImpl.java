package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.ReservationRepository;
import RoomReservationSystem.service.ReservationService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.UserService;

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
    private UserService userService;
    
    @Autowired
    private StatusService statusService;

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
     * Beállítjuk a foglaláshoz tartozó tantermet, tantárgyat, felhasználót és státuszt is
     * A státusz alapértelmezetten "PENDING", azaz feldolgozás alatt lesz
     * @param   reservation     A rögzíteni kívánt foglalás
     * @return                  A rögzített foglalás
     */
    @Override
    public Reservation save(Reservation reservation){        
        return reservationRepository.save(reservation);
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
     * @return 
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
