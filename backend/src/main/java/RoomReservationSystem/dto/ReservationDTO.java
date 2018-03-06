package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.Subject;
import RoomReservationSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String username;
    private String name;
    private String subject;
    private String building;
    private String room;
    private String day;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    
    public static ReservationDTO convertToReservationDto(
            User user, 
            Subject subject,
            Building building,
            Classroom classroom, 
            Reservation reservation) {
        
        return new ReservationDTO(
                user.getUsername(),
                user.getName(),
                subject.getName(),
                building.getName(),
                classroom.getName(),
                reservation.getDay(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }
}
