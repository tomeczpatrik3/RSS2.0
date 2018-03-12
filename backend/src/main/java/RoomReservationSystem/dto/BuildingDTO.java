package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
    private String name;
    
    public static BuildingDTO toBuildingDTO(Building building) {
        return new BuildingDTO(building.getName());
    }
    
    public static List<BuildingDTO> toBuildingDTOList(List<Building> buildings) {
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        buildings.forEach((building) -> {
            buildingDTOs.add(toBuildingDTO(building));
        });
        return buildingDTOs;
    }
}
