package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.repository.ReservationRepository;
import RoomReservationSystem.service.ReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Override
    public void delete(Reservation res){
        reservationRepository.delete(res);
    }
    
    @Override
    public void deleteAll(){
        reservationRepository.deleteAll();
    }
    
    @Override
    public Reservation save(Reservation res){       
        return reservationRepository.save(res);
    }
    
    @Override
    public Iterable<Reservation> findAll(){
        return reservationRepository.findAll();
    }
    
}
