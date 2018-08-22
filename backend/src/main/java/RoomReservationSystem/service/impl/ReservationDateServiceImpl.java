package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.repository.ReservationDateRepository;
import RoomReservationSystem.service.ReservationDateService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static RoomReservationSystem.util.DateUtils.getDateTime;
import static RoomReservationSystem.util.DateUtils.getDateTimeString;
import static RoomReservationSystem.util.DateUtils.getDayOfWeek;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

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
        String startDateTimeStr = reservationDate.getStartDateTime().toString();
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
        reservationDateRepository.findAll().stream().filter((reservationDate) -> (reservationDate.getStartDateTime().toDate().equals(date) && reservationDate.getEndDateTime().toDate().equals(date))).forEachOrdered((reservationDate) -> {
            reservations.add(reservationDate.getReservation());
        });
        return reservations;
    }
    
    @Override
    public List<ReservationDate> save(Reservation reservation, Semester semester, String day, String startTime, String endTime) {
        LocalDate start = semester.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = semester.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        List<ReservationDate> savedDates = new ArrayList<>();
        
        while (!start.getDayOfWeek().equals(dayOfWeek)) {
            start.plusDays(1);
        }
        
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(7)) {
            savedDates.add(save(
                    reservation,
                    getDateTimeString(date, startTime),
                    getDateTimeString(date, endTime)
            ));
        }
        
        return savedDates;
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
