package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az eseményekre vonatkozó foglalásokhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EventReservationDTO extends ReservationDTO {

    /*A foglalás neve*/
    private String name;
    /*A foglalás kezdete*/
    private String startDate;
    /*A foglalás vége*/
    private String endDate;

    /**
     * Az osztály konstukrota
     *
     * @param id Az azonosító
     * @param username A felhasználónév
     * @param building Az épület
     * @param classroom A tanterem
     * @param status A státusz
     * @param name A név
     * @param startDate A kezdeti dátum
     * @param endDate A befejezési dátum
     * @param note A megjegyzés
     */
    public EventReservationDTO(
            long id,
            String username,
            String building,
            String classroom,
            String status,
            String name,
            String startDate,
            String endDate,
            String note
    ) {
        super(id, username, building, classroom, status, note);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Az osztály üres konstruktora
     */
    public EventReservationDTO() {
    }

    /**
     * Az EventReservation objektumból EventReservationDTO objektum
     * létrehozásáért felelős metódus
     *
     * @param reservation A EventReservation objektum
     * @return A EventReservationDTO objektum
     */
    public static EventReservationDTO toEventReservationDTO(EventReservation reservation) {
        ReservationDate rDate = reservation.getDateList().get(0);

        return new EventReservationDTO(
                reservation.getId(),
                reservation.getUser().getUsername(),
                reservation.getClassroom().getBuilding().getName(),
                reservation.getClassroom().getName(),
                reservation.getStatus().getName(),
                reservation.getName(),
                rDate.getStart().toString(),
                rDate.getEnd().toString(),
                reservation.getNote()
        );
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
