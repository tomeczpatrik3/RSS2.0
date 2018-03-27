package RoomReservationSystem.dto;

import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import RoomReservationSystem.model.Reservation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String semester;
    private UserDTO user;
    private SubjectDTO subject;
    private ClassroomDTO classroom;
    private String note;
    private String status;
    private String type;
    private Date[] dates;
    
    public static ReservationDTO toReservationDTO(Reservation reservation) { 
        return new ReservationDTO(
                reservation.getSemester().getName(),
                toUserDTO(reservation.getUser()),
                toSubjectDTO(reservation.getSubject()),
                toClassroomDTO(reservation.getClassroom()),
                reservation.getNote(),
                reservation.getStatus().getName(),
                "",
                Collections.emptyList()
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
