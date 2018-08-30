package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A tantárgyakra vonatkozó foglalásokhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClassReservationDTO extends ReservationDTO {

    private String subjectCode;
    /*A tantárgy amire a foglalás vonatkozik*/
    private String start;
    private String end;

    public ClassReservationDTO(
            String username,
            String building,
            String classroom,
            String status,
            String subjectCode,
            String start,
            String end,
            String note
    ) {
        super(username, building, classroom, status, note);
        this.subjectCode = subjectCode;
        this.start = start;
        this.end = end;
    }

    /**
     * Az ClassReservation objektumból ClassReservationDTO objektum
     * létrehozásáért felelős metódus
     *
     * @param reservation A ClassReservation objektum
     * @return A ClassReservationDTO objektum
     */
    public static ClassReservationDTO toClassReservationDTO(ClassReservation reservation) {
        if (reservation.getDateList() != null && reservation.getDateList().size() == 1) {
            ReservationDate rDate = reservation.getDateList().get(0);
            return new ClassReservationDTO(
                    reservation.getUser().getUsername(),
                    reservation.getClassroom().getBuilding().getName(),
                    reservation.getClassroom().getName(),
                    reservation.getStatus().getName(),
                    reservation.getSubject().getCode(),
                    rDate.getStart().toString(),
                    rDate.getEnd().toString(),
                    reservation.getNote()
            );
        } else {
            return null;
        }
    }

    /**
     * Több ClassReservation objektumok ClassReservationDTO objektumokká
     * alakításáért felelős metódus
     *
     * @param reservations A ClassReservation objektumok egy listában
     * @return A ClassReservationDTO objektumok egy listában
     */
    public static List<ClassReservationDTO> toClassReservationDTOList(List<ClassReservation> reservations) {
        List<ClassReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toClassReservationDTO(reservation));
        });
        return reservationDTOs;
    }
}
