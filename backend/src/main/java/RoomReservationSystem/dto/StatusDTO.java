package RoomReservationSystem.dto;

import RoomReservationSystem.model.Status;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az állapotokhoz tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    /*A státusz neve*/
    private String name;
    /*A státuszhoz tartozó üzenet*/
    private String message;

    /**
     * A Status objektumból StatusDTO objektum létrehozásáért felelős metódus
     *
     * @param status A Status objektum
     * @return A StatusDTO objektum
     */
    public static StatusDTO toStatusDTO(Status status) {
        return new StatusDTO(status.getName(), status.getMessage());
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
