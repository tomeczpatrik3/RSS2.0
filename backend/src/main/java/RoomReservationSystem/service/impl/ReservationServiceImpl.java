package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ReservationDTO;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTOList;
import RoomReservationSystem.model.Reservation;
import static RoomReservationSystem.model.Reservation.toReservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.Ticket;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.ReservationRepository;
import RoomReservationSystem.service.ReservationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private ClassroomServiceImpl classroomService;
        
    @Autowired
    private SubjectServiceImpl subjectService;
    
    @Autowired
    private TicketServiceImpl ticketService;
    //---
    
    @Override
    public void delete(Reservation res){
        reservationRepository.delete(res);
    }
    
    @Override
    public void deleteAll(){
        reservationRepository.deleteAll();
    }
    
    /*
        A megfelelő adatokkal mentjük a foglalást:
    */
    @Override
    public Reservation save(ReservationDTO resDTO){       
        User user = userService.findByUsername(resDTO.getUsername());
        
        Reservation reservation =  reservationRepository.save(toReservation(
                    resDTO,
                    classroomService.findByName(resDTO.getRoom()),
                    subjectService.findByCode(resDTO.getSubjectCode()),
                    user
            ));
        
        ticketService.save(reservation, user);
        
        return reservation;
    }
    
    @Override
    public Reservation findById(int id){       
        return reservationRepository.findById(id);
    }
    
    /*
        Reservation --> ReservationDTO
    */
    @Override
    public List<ReservationDTO> getAll(){
        return toReservationDTOList(reservationRepository.findAll());
    }
    
    @Override
    public List<ReservationDTO> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return toReservationDTOList(
                reservationRepository.findByUser(user)
        );
    }
    
    @Override
    public List<ReservationDTO> getAccepted() {
        List<Reservation> reservations = new ArrayList<>();
        for (Ticket ticket: ticketService.findByStatusHelper(Status.ACCEPTED))
            reservations.add(ticket.getReservation());
        
        return toReservationDTOList(reservations);
    }
}
