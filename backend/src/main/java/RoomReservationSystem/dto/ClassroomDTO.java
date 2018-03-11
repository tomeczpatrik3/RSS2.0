package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private String name;
    private boolean hasPC;
    private boolean hasProjector;
    private int chairs;
    private String building;
    
    public static ClassroomDTO toClassroomDTO(Classroom classroom){
        return new ClassroomDTO(
                classroom.getName(),
                classroom.isHasPc(),
                classroom.isHasProjector(),
                classroom.getChairs(),
                classroom.getBuilding().getName()
        );
    }
}
