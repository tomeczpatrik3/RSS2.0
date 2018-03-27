package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Az épületekhez tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
    private String name;    /*Az épület neve*/
    
    /**
     * A Building objektumból BuildingDTO objektum létrehozásáért felelős metódus
     * @param   building    A Building objektum
     * @return              A BuildingDTO objektum
     */
    public static BuildingDTO toBuildingDTO(Building building) {
        return new BuildingDTO(building.getName());
    }
    
    /**
     * Több Building objektum BuildingDTO objektummá alakításáért felelős metódus
     * @param   buildings   A Building objektumok egy listában
     * @return              A BuildingDTO objektumok egy listában
     */
    public static List<BuildingDTO> toBuildingDTOList(List<Building> buildings) {
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        buildings.forEach((building) -> {
            buildingDTOs.add(toBuildingDTO(building));
        });
        return buildingDTOs;
    }
}
