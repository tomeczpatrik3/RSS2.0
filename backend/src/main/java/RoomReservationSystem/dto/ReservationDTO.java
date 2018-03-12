package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Status;
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
    private String status;
    
    public static ReservationDTO toReservationDTO(Reservation reservation) { 
        return new ReservationDTO(
                reservation.getUser().getUsername(),
                reservation.getUser().getName(),
                reservation.getSubject().getName(),
                reservation.getSubject().getCode(),
                reservation.getClassroom().getBuilding().getName(),
                reservation.getClassroom().getName(),
                reservation.getDay(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                reservation.getStatus().getName()
        );
    }
    
    public static List<ReservationDTO> toReservationDTOList(List<Reservation> reservations) {
        List<ReservationDTO> resDtos = new ArrayList<>();
        reservations.forEach((res) -> {
            resDtos.add(toReservationDTO(res));
        });
        return resDtos;        
    }
}
