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
import RoomReservationSystem.model.reservation.ReservationDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EventReservationDTO extends ReservationDTO {

    private String name;
    /*A foglalás neve*/
    private String start;
    private String end;

    public EventReservationDTO(
            String username,
            String building,
            String classroom,
            String status,
            String name,
            String start,
            String end,
            String note
    ) {
        super(username, building, classroom, status, note);
        this.name = name;
        this.start = start;
        this.end = end;
    }

    /**
     * Az EventReservation objektumból EventReservationDTO objektum
     * létrehozásáért felelős metódus
     *
     * @param reservation A EventReservation objektum
     * @return A EventReservationDTO objektum
     */
    public static EventReservationDTO toEventReservationDTO(EventReservation reservation) {
        if (reservation.getDateList() != null && reservation.getDateList().size() == 1) {       
            ReservationDate rDate = reservation.getDateList().get(0);
            
            return new EventReservationDTO(
                    reservation.getUser().getUsername(),
                    reservation.getClassroom().getBuilding().getName(),
                    reservation.getClassroom().getName(),
                    reservation.getStatus().getName(),
                    reservation.getName(),
                    rDate.getStart().toString(),
                    rDate.getEnd().toString(),
                    reservation.getNote()
            );
        } else {
            return null;
        }
    }

    /**
     * Több EventReservation objektumok EventReservationDTO objektumokká
     * alakításáért felelős metódus
     *
     * @param reservations A EventReservation objektumok egy listában
     * @return A EventReservationDTO objektumok egy listában
     */
    public static List<EventReservationDTO> toEventReservationDTOList(List<EventReservation> reservations) {
        List<EventReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toEventReservationDTO(reservation));
        });
        return reservationDTOs;
    }
}
