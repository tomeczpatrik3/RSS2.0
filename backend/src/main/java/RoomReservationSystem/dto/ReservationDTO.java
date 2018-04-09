package RoomReservationSystem.dto;

import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
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
 * A foglalások megjelenítéséhez tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String name;                /*A foglalás neve*/
    private String semester;            /*A félév (neve)*/
    private UserDTO user;               /*A felhasználó akihez a foglalás tarotzik*/
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    private BuildingDTO building;       /*Az épület, amiben a tanterem található*/
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
        String semesterName;
        SubjectDTO subjectDTO;
        
        switch (reservation.getType().getName().toUpperCase()) {
            case "SIMPLE":
                semesterName = reservation.getSemester().getName();
                subjectDTO = toSubjectDTO(reservation.getSubject());
                break;
            case "EVENT":
                semesterName = "";
                subjectDTO = null;
                break;
            default: //SEMESTER
                semesterName = reservation.getSemester().getName();
                subjectDTO = toSubjectDTO(reservation.getSubject());
                break;
        }
        return new ReservationDTO(
                reservation.getName(),
                semesterName,
                toUserDTO(reservation.getUser()),
                subjectDTO,
                toBuildingDTO(reservation.getClassroom().getBuilding()),
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
