package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.BuildingDTO;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
import RoomReservationSystem.dto.ClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import RoomReservationSystem.dto.StatusDTO;
import static RoomReservationSystem.dto.StatusDTO.toStatusDTO;
import RoomReservationSystem.dto.UserDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import RoomReservationSystem.model.reservation.EventReservation;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class EventReservationDTO extends ReservationDTO{
    private String name;                /*A foglalás neve*/
    
    public EventReservationDTO(
            UserDTO userDTO,
            BuildingDTO buildingDTO,
            ClassroomDTO classroomDTO,
            StatusDTO statusDTO,
            String name,
            String note
    ) {
        super(userDTO, buildingDTO, classroomDTO, statusDTO, note);
        this.name = name;
    }
    
    /**
     * Az EventReservation objektumból EventReservationDTO objektum létrehozásáért felelős metódus
     * @param   reservation     A EventReservation objektum
     * @return           A EventReservationDTO objektum
     */
    public static EventReservationDTO toEventReservationDTO(EventReservation reservation) {
        return new EventReservationDTO(
                toUserDTO(reservation.getUser()),
                toBuildingDTO(reservation.getClassroom().getBuilding()),
                toClassroomDTO(reservation.getClassroom()),
                toStatusDTO(reservation.getStatus()),
                reservation.getName(),
                reservation.getNote()
        );
    }
    
    /**
     * Több EventReservation objektumok EventReservationDTO objektumokká alakításáért felelős metódus
     * @param   reservations    A EventReservation objektumok egy listában
     * @return                  A EventReservationDTO objektumok egy listában
     */
    public static List<EventReservationDTO> toEventReservationDTOList(List<EventReservation> reservations) {
        List<EventReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toEventReservationDTO(reservation));
        });
        return reservationDTOs;        
    }
}
