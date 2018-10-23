package RoomReservationSystem.dto;

import RoomReservationSystem.model.Classroom;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A tantermekhez tartozó DTO osztály
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassroomDTO extends BaseDTO {

    /*A tanterem neve*/
    private String name;
    /*A tanteremben található-e PC?*/
    private boolean hasPC;
    /*A tanteremben található-e projektor?*/
    private boolean hasProjector;
    /*A tanteremben található székek száma*/
    private int chairs;
    /*A tanteremhez tartozó épület neve*/
    private String building;

    /**
     * Az osztály konstruktora
     *
     * @param id Az azonosító
     * @param name A tanterem neve
     * @param hasPC Van-e PC
     * @param hasProjector Van-e projektor
     * @param chairs A székek száma
     * @param building Az épület neve
     */
    public ClassroomDTO(
            long id,
            String name,
            boolean hasPC,
            boolean hasProjector,
            int chairs,
            String building
    ) {
        super(id);
        this.name = name;
        this.hasPC = hasPC;
        this.hasProjector = hasProjector;
        this.chairs = chairs;
        this.building = building;
    }

    /**
     * Az osztály üres konstruktora
     */
    public ClassroomDTO() {
    }

    /**
     * A Classroom objektumból ClassroomDTO objektum létrehozásáért felelős
     * metódus
     *
     * @param classroom A Classroom objektum
     * @return A ClassroomDTO objektum
     */
    public static ClassroomDTO toClassroomDTO(Classroom classroom) {
        return new ClassroomDTO(
                classroom.getId(),
                classroom.getName(),
                classroom.isHasPc(),
                classroom.isHasProjector(),
                classroom.getChairs(),
                classroom.getBuilding().getName()
        );
    }

    /**
     * Több Classroom objektum ClassroomDTO objektummá alakításáért felelős
     * metódus
     *
     * @param classrooms A Classroom objektumok egy listában
     * @return A ClassroomDTO objektumok egy listában
     */
    public static List<ClassroomDTO> toClassroomDTOList(List<Classroom> classrooms) {
        List<ClassroomDTO> classroomDTOs = new ArrayList<>();
        classrooms.forEach((classroom) -> {
            classroomDTOs.add(toClassroomDTO(classroom));
        });
        return classroomDTOs;
    }
}
