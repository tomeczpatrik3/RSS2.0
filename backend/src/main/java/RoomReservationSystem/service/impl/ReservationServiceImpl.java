package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ReservationDTO;
import RoomReservationSystem.model.Reservation;
import static RoomReservationSystem.model.Reservation.toReservation;
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
                    userService.findByUsername(resDTO.getUsername())
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
        List<Reservation> reservations =  reservationRepository.findAll();
        List<ReservationDTO> resDtos = new ArrayList<>();
        
        for (Reservation res: reservations) {
            resDtos.add(ReservationDTO.convertToReservationDto(
                    res.getUser(), 
                    res.getSubject(),
                    res.getClassroom().getBuilding(),
                    res.getClassroom(), 
                    res
            ));
        }
        return resDtos;
    }
    
    @Override
    public List<ReservationDTO> findByUsername(String username) {
        List<Reservation> reservations =  reservationRepository.findAll();
        List<ReservationDTO> resDtos = new ArrayList<>();
        
        for (Reservation res: reservations) {
            if (res.getUser().getUsername().equals(username)) {
                resDtos.add(ReservationDTO.convertToReservationDto(
                        res.getUser(), 
                        res.getSubject(),
                        res.getClassroom().getBuilding(),
                        res.getClassroom(), 
                        res
                ));                
            }
        }
        return resDtos;
    }
}
