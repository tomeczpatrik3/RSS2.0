package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.BuildingDTO;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
import RoomReservationSystem.dto.ClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import RoomReservationSystem.dto.StatusDTO;
import static RoomReservationSystem.dto.StatusDTO.toStatusDTO;
import RoomReservationSystem.dto.SubjectDTO;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;
import RoomReservationSystem.dto.UserDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import RoomReservationSystem.model.reservation.ClassReservation;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A tantárgyakra vonatkozó foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class ClassReservationDTO extends ReservationDTO{
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    
    public ClassReservationDTO(
            UserDTO userDTO,
            BuildingDTO buildingDTO,
            ClassroomDTO classroomDTO,
            SubjectDTO subjectDTO,
            StatusDTO statusDTO,
            String note
    ) {
        super(userDTO, buildingDTO, classroomDTO, statusDTO, note);
        this.subject = subjectDTO;
    }
    
    /**
     * Az ClassReservation objektumból ClassReservationDTO objektum létrehozásáért felelős metódus
     * @param   reservation     A ClassReservation objektum
     * @return           A ClassReservationDTO objektum
     */
    public static ClassReservationDTO toClassReservationDTO(ClassReservation reservation) {
        return new ClassReservationDTO(
                toUserDTO(reservation.getUser()),
                toBuildingDTO(reservation.getClassroom().getBuilding()),
                toClassroomDTO(reservation.getClassroom()),
                toSubjectDTO(reservation.getSubject()),
                toStatusDTO(reservation.getStatus()),
                reservation.getNote()
        );
    }
    
    /**
     * Több ClassReservation objektumok ClassReservationDTO objektumokká alakításáért felelős metódus
     * @param   reservations    A ClassReservation objektumok egy listában
     * @return                  A ClassReservationDTO objektumok egy listában
     */
    public static List<ClassReservationDTO> toClassReservationDTOList(List<ClassReservation> reservations) {
        List<ClassReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toClassReservationDTO(reservation));
        });
        return reservationDTOs;        
    }
}
