package RoomReservationSystem.dto;

import RoomReservationSystem.model.Status;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az állapotokhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StatusDTO extends BaseDTO {

    /*A státusz neve*/
    private String name;
    /*A státuszhoz tartozó üzenet*/
    private String message;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param name A státusz neve
     * @param message A státusz üzenete
     */
    public StatusDTO(
            long id,
            String name,
            String message
    ) {
        super(id);
        this.name = name;
        this.message = message;
    }
    
    /**
     * Az osztály üres konstruktora
     */
    public StatusDTO() {
    }

    /**
     * A Status objektumból StatusDTO objektum létrehozásáért felelős metódus
     *
     * @param status A Status objektum
     * @return A StatusDTO objektum
     */
    public static StatusDTO toStatusDTO(Status status) {
        return new StatusDTO(status.getId(), status.getName(), status.getMessage());
    }

    /**
     * Több Status objektumok StatusDTO objektumokká alakításáért felelős
     * metódus
     *
     * @param statuses A Status objektumok egy listában
     * @return A StatusDTO objektumok egy listában
     */
    public static List<StatusDTO> toTypeDTOList(List<Status> statuses) {
        List<StatusDTO> statusDTOs = new ArrayList<>();
        statuses.forEach((status) -> {
            statusDTOs.add(toStatusDTO(status));
        });
        return statusDTOs;
    }
}
