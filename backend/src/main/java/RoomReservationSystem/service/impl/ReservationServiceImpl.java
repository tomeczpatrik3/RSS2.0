package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ReservationDTO;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTO;
import static RoomReservationSystem.dto.ReservationDTO.toReservationDTOList;
import RoomReservationSystem.model.Reservation;
import static RoomReservationSystem.model.Reservation.toReservation;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.ReservationRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.ReservationService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ClassroomService classroomService;
        
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private StatusService statusService;

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
        return reservationRepository.save(toReservation(
                    resDTO,
                    classroomService.findByName(resDTO.getRoom()),
                    subjectService.findByCode(resDTO.getSubjectCode()),
                    userService.findByUsername(resDTO.getUsername()),
                    statusService.findByName("PENDING")
            ));
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
    public List<ReservationDTO> findByStatus(String statusName) {
        Status status = statusService.findByName(statusName);
        return toReservationDTOList(reservationRepository.findByStatus(status));
    }
    
    @Override
    public ReservationDTO setStatus(int id, String statusName) {
        Reservation res = reservationRepository.findById(id);
        res.setStatus(statusService.findByName(statusName));
        return toReservationDTO(res);
    }
}
