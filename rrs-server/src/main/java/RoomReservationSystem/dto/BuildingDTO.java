package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Az épületekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BuildingDTO extends BaseDTO {

    /*Az épület neve*/
    private String name;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param name Az épület neve
     */
    public BuildingDTO(
            long id,
            String name
    ) {
        super(id);
        this.name = name;
    }

    /**
     * Az osztály üres konstruktora
     */
    public BuildingDTO() {
    }

    /**
     * A Building objektumból BuildingDTO objektum létrehozásáért felelős
     * metódus
     *
     * @param building A Building objektum
     * @return A BuildingDTO objektum
     */
    public static BuildingDTO toBuildingDTO(Building building) {
        return new BuildingDTO(building.getId(), building.getName());
    }

    /**
     * Több Building objektum BuildingDTO objektummá alakításáért felelős
     * metódus
     *
     * @param buildings A Building objektumok egy listában
     * @return A BuildingDTO objektumok egy listában
     */
    public static List<BuildingDTO> toBuildingDTOList(List<Building> buildings) {
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        buildings.forEach((building) -> {
            buildingDTOs.add(toBuildingDTO(building));
        });
        return buildingDTOs;
    }
}
