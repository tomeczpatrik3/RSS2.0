package RoomReservationSystem.dto;

import RoomReservationSystem.model.Reservation;
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
    private String semester;
    private String note;
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
                reservation.getSemester().getName(),
                reservation.getNote(),
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
