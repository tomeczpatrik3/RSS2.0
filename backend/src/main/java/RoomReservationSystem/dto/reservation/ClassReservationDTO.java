package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.util.DateUtils;
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

    /*A tantárgy amire a foglalás vonatkozik*/
    private String subjectCode;
    /*A foglalás kezdete*/
    private String[] startDates;
    /*A foglalás vége*/
    private String[] endDates;
    /*A szemeszter*/
    private String semester;

    /**
     * Az osztály konstruktora
     *
     * @param username A felhasználónév
     * @param building Az épület
     * @param classroom A tanterem
     * @param status A státusz
     * @param subjectCode A tárgykód
     * @param startDates A kezdeti dátumok
     * @param endDates A befejezési dátumok
     * @param semester A szemeszter
     * @param note A megjegyzés
     */
    public ClassReservationDTO(
            String username,
            String building,
            String classroom,
            String status,
            String subjectCode,
            String[] startDates,
            String[] endDates,
            String semester,
            String note
    ) {
        super(username, building, classroom, status, note);
        this.subjectCode = subjectCode;
        this.startDates = startDates;
        this.endDates = endDates;
        this.semester = semester;
    }

    /**
     * Az ClassReservation objektumból ClassReservationDTO objektum
     * létrehozásáért felelős metódus
     *
     * @param reservation A ClassReservation objektum
     * @return A ClassReservationDTO objektum
     */
    public static ClassReservationDTO toClassReservationDTO(ClassReservation reservation) {
        return new ClassReservationDTO(
                reservation.getUser().getUsername(),
                reservation.getClassroom().getBuilding().getName(),
                reservation.getClassroom().getName(),
                reservation.getStatus().getName(),
                reservation.getSubject().getCode(),
                DateUtils.getStartDates(reservation),
                DateUtils.getEndDates(reservation),
                reservation.getSemester().getName(),
                reservation.getNote()
        );
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
