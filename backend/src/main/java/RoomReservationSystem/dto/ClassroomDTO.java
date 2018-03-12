package RoomReservationSystem.dto;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import java.util.ArrayList;
import java.util.List;
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
    
    public static List<ClassroomDTO> toClassroomDTOList(List<Classroom> classrooms) {
        List<ClassroomDTO> classroomDTOs = new ArrayList<>();
        classrooms.forEach((classroom) -> {
            classroomDTOs.add(toClassroomDTO(classroom));
        });
        return classroomDTOs;
    }
}
