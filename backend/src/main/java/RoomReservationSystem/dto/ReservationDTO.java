package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String username;
    private String name;
    private String subjectName;
    private String subjectCode;
    private String building;
    private String room;
    private String day;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    
    public static ReservationDTO toReservationDTO(
            User user, 
            Subject subject,
            Building building,
            Classroom classroom, 
            Reservation reservation) {
        
        return new ReservationDTO(
                user.getUsername(),
                user.getName(),
                subject.getName(),
                subject.getCode(),
                building.getName(),
                classroom.getName(),
                reservation.getDay(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }
    
    public static List<ReservationDTO> toReservationDTOList(List<Reservation> reservations) {
        List<ReservationDTO> resDtos = new ArrayList<>();
        
        for (Reservation res: reservations) {
            resDtos.add(ReservationDTO.toReservationDTO(
                    res.getUser(), 
                    res.getSubject(),
                    res.getClassroom().getBuilding(),
                    res.getClassroom(), 
                    res
            ));                
        }
        return resDtos;        
    }
}
