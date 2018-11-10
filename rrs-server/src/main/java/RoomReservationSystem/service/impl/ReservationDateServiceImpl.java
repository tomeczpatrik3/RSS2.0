package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.repository.ReservationDateRepository;
import RoomReservationSystem.service.ReservationDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A foglalásokhoz tartozó dátumokkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class ReservationDateServiceImpl implements ReservationDateService {

    @Autowired
    ReservationDateRepository repository;

    @Override
    public ReservationDate save(ReservationDate date) {
        return repository.save(date);
    }

    @Override
    public void deleteByReservation(Reservation reservation) {
        repository.deleteByReservation(reservation);
    }
}
