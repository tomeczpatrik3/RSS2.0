package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.repository.ReservationDateRepository;
import RoomReservationSystem.service.ReservationDateService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static RoomReservationSystem.util.DateUtils.getDateTime;
import java.util.Date;

/**
 * A foglalásokhoz tartozó dátumokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class ReservationDateServiceImpl implements ReservationDateService {
    
    @Autowired
    private ReservationDateRepository reservationDateRepository;
    
    /**
     * Egy adott foglalt dátum törlését megvalósító függvény
     * @param   reservationDate     A törlendő dátum
     */
    @Override
    public void delete(ReservationDate reservationDate) {
        reservationDateRepository.delete(reservationDate);
    }
    
    /**
     * Egy adott foglaláshoz tartozó dátumok törlését megvalósító függvény
     * @param   reservation     A foglalás
     */
    @Override
    public void deleteByReservation(Reservation reservation) {
        reservationDateRepository.deleteByReservation(reservation);
    }
    
    /**
     * Egy foglaláshoz tartozó dátum mentését megvalósító függvény
     * @param   reservationDate     A dátum amit menteni szeretnénk
     * @return                      A mentett dátum, ha sikerült
     */
    @Override
    public ReservationDate save(ReservationDate reservationDate) {
        return reservationDateRepository.save(reservationDate);
    }
    
    /**
     * Egy dátum alapján a foglalások keresését megvalósító függvény
     * @param   date    A dátum
     * @return          A foglalások egy listában
     */
    @Override
    public List<Reservation> findByDate(Date date) {
        List<Reservation> reservations = new ArrayList<>();
        reservationDateRepository.findAll().stream().filter((reservationDate) -> (reservationDate.getStartTime().toDate().equals(date) && reservationDate.getEndTime().toDate().equals(date))).forEachOrdered((reservationDate) -> {
            reservations.add(reservationDate.getReservation());
        });
        return reservations;
    }
    
    /**
     * A foglalásokhoz tartozó dátumok mentéséért felelős függvény
     * @param   reservation     A foglalás amihez tartozó dátumokat szeretnénk menteni
     * @param   dates           A dátumok egy string tömbben
     * @return 
     */
    @Override
    public List<ReservationDate> saveReservationDates(Reservation reservation, String[] dates) {
        List<ReservationDate> reservationDates = new ArrayList<>();
        for(int i=0; i<dates.length; i+=2) {
            reservationDates.add(save(new ReservationDate(
                            getDateTime(dates[i]),
                            getDateTime(dates[i+1]),
                            reservation
                    )
            ));
        }
        return reservationDates;
    }
    
    @Override
    public ReservationDate save(
            Reservation reservation,
            String startDateTime,
            String endDateTime
    ) {
        return save(new ReservationDate(
                getDateTime(startDateTime),
                getDateTime(endDateTime),
                reservation
        ));
    }

}
