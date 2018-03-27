package RoomReservationSystem.dto;

import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import RoomReservationSystem.model.Reservation;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String semester;            /*A félév (neve)*/
    private UserDTO user;               /*A felhasználó akihez a foglalás tarotzik*/
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    private ClassroomDTO classroom;     /*A tanterem amire a foglalás vonatkozik*/
    private String note;                /*A foglaláshoz tartozó jegyzet*/
    private String status;              /*A foglalás státusza*/
    private String[] dates;             /*A foglaláshoz tartozó időpontok*/
    
    /**
     * A Reservation objektumból ReservationDTO objektum létrehozásáért felelős metódus
     * @param   reservation A Reservation objektum
     * @return              A ReservationDTO objektum
     */
    public static ReservationDTO toReservationDTO(Reservation reservation) { 
        return new ReservationDTO(
                reservation.getSemester().getName(),
                toUserDTO(reservation.getUser()),
                toSubjectDTO(reservation.getSubject()),
                toClassroomDTO(reservation.getClassroom()),
                reservation.getNote(),
                reservation.getStatus().getName(),
                reservation.getDates()
        );
    }
    
    /**
     * Több Reservation objektum ReservationDTO objektummá alakításáért felelős metódus
     * @param   reservations    A Reservation objektumok egy listában 
     * @return                  A ReservationDTO objektumok egy listában
     */
    public static List<ReservationDTO> toReservationDTOList(List<Reservation> reservations) {
        List<ReservationDTO> resDtos = new ArrayList<>();
        reservations.forEach((res) -> {
            resDtos.add(toReservationDTO(res));
        });
        return resDtos;        
    }
}
