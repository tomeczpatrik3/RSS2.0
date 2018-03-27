package RoomReservationSystem.dto;

import RoomReservationSystem.model.Classroom;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A tantermekhez tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private String name;            /*A tanterem neve*/
    private boolean hasPC;          /*A tanteremben található-e PC?*/
    private boolean hasProjector;   /*A tanteremben található-e projektor?*/
    private int chairs;             /*A tanteremben található székek száma*/
    private String building;        /*A tanteremhez tartozó épület neve*/
    
    /**
     * A Classroom objektumból ClassroomDTO objektum létrehozásáért felelős metódus
     * @param   classroom   A Classroom objektum
     * @return              A ClassroomDTO objektum
     */
    public static ClassroomDTO toClassroomDTO(Classroom classroom){
        return new ClassroomDTO(
                classroom.getName(),
                classroom.isHasPc(),
                classroom.isHasProjector(),
                classroom.getChairs(),
                classroom.getBuilding().getName()
        );
    }
    
    /**
     * Több Classroom objektum ClassroomDTO objektummá alakításáért felelős metódus
     * @param   classrooms  A Classroom objektumok egy listában
     * @return              A ClassroomDTO objektumok egy listában
     */
    public static List<ClassroomDTO> toClassroomDTOList(List<Classroom> classrooms) {
        List<ClassroomDTO> classroomDTOs = new ArrayList<>();
        classrooms.forEach((classroom) -> {
            classroomDTOs.add(toClassroomDTO(classroom));
        });
        return classroomDTOs;
    }
}
