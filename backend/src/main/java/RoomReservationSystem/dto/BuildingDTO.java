package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
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
}
