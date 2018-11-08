package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.enums.ReservationType;
import lombok.Data;

/**
 * A foglalásokhoz tartozó információkat tartalmazo DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
public class ReservationInfoDTO {

    /*A foglalás azonosítója*/
    private long id;
    /*A foglalás típusa*/
    private ReservationType type;
    /*A foglaló személy neve*/
    private String name;
    /*A foglaláshoz tartozó épület*/
    private String building;
    /*A foglaláshoz tartozó tanterem*/
    private String classroom;
    /*A foglaláshoz tartozó megjegyzés*/
    private String note;

    /*A foglaláshoz tartozó név (ha esemény foglalás)*/
    private String eventName;

    /*A foglaláshoz tartozó tantárgy*/
    private String subject;
    /*A foglaláshoz tartozó szemeszter*/
    private String semester;

    /**
     * Az osztály alapértelmezett adattagjainak inicializálására szolgáló
     * konstruktor
     *
     * @param id Az azonosító
     * @param type A típus
     * @param name A foglaló személy neve
     * @param building Az épület
     * @param classroom A tanterem
     * @param note A megjegyzés
     */
    public ReservationInfoDTO(
            long id,
            ReservationType type,
            String name,
            String building,
            String classroom,
            String note
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.building = building;
        this.classroom = classroom;
    }

    /**
     *
     * @param id Az azonosító
     * @param type A típus
     * @param name A foglaló személy neve
     * @param building Az épület
     * @param classroom A tanterem
     * @param note A megjegyzés
     * @param subject A tantárgy neve
     * @param semester A szemeszter "neve"
     */
    public ReservationInfoDTO(long id, ReservationType type, String name, String building, String classroom, String note, String subject, String semester) {
        this(id, type, name, building, classroom, note);
        this.subject = subject;
        this.semester = semester;
        this.eventName = "";
    }

    /**
     *
     * @param id Az azonosító
     * @param type A típus
     * @param name A foglaló személy neve
     * @param building Az épület
     * @param classroom A tanterem
     * @param note A megjegyzés
     * @param eventName Az esemény neve
     */
    public ReservationInfoDTO(long id, ReservationType type, String name, String building, String classroom, String note, String eventName) {
        this(id, type, name, building, classroom, note);
        this.eventName = eventName;
        this.subject = "";
        this.semester = "";
    }

}
