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
import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.model.reservation.SimpleClassReservation;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import static org.apache.tomcat.jni.Lock.name;

/**
 * Az egyszerű foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReservationDTO extends ReservationDTO{
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    
    public SimpleReservationDTO(
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
     * Az SimpleReservation objektumból SimpleReservationDTO objektum létrehozásáért felelős metódus
     * @param   reservation     A SimpleReservation objektum
     * @return           A SimpleReservationDTO objektum
     */
    public static SimpleReservationDTO toSimpleReservationDTO(SimpleClassReservation reservation) {
        return new SimpleReservationDTO(
                toUserDTO(reservation.getUser()),
                toBuildingDTO(reservation.getClassroom().getBuilding()),
                toClassroomDTO(reservation.getClassroom()),
                toSubjectDTO(reservation.getSubject()),
                toStatusDTO(reservation.getStatus()),
                reservation.getNote()
        );
    }
    
    /**
     * Több SimpleReservation objektumok SimpleReservationDTO objektumokká alakításáért felelős metódus
     * @param   reservations    A SimpleReservation objektumok egy listában
     * @return                  A SimpleReservationDTO objektumok egy listában
     */
    public static List<SimpleReservationDTO> toSimpleReservationDTOList(List<SimpleClassReservation> reservations) {
        List<SimpleReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toSimpleReservationDTO(reservation));
        });
        return reservationDTOs;        
    }
}
