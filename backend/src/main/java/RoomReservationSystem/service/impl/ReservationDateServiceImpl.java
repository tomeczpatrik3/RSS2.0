package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.repository.ReservationDateRepository;
import RoomReservationSystem.service.ReservationDateService;
import static RoomReservationSystem.util.DateUtils.getDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * A foglalásokhoz tartozó dátumok mentéséért felelős függvény
     * @param   reservation     A foglalás amihez tartozó dátumokat szeretnénk menteni
     * @param   dates           A dátumok egy string tömbben
     * @return 
     */
    @Override
    public List<ReservationDate> saveReservationDates(Reservation reservation, String[] dates) {
        List<ReservationDate> reservationDates = new ArrayList<>();
        for(int i=0; i<dates.length; i+=2) {
            reservationDates.add(save(
                    new ReservationDate(
                            getDate(dates[i]),
                            getDate(dates[i+1]),
                            reservation
                    )
            ));
        }
        return reservationDates;
    }
}
