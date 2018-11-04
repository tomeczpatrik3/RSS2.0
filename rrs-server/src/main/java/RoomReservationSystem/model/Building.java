package RoomReservationSystem.model;

import RoomReservationSystem.dto.BuildingDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Épület entitás
 *
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Table(name = "BUILDINGS")
public class Building extends BaseEntity {

    /*Az épület neve*/
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME", unique = true)
    private String name;

    /*Az épülethez tartozó tantermek listája*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building", targetEntity = Classroom.class)
    private List<Classroom> classroomList;

    /**
     * Az osztály üres konstruktora
     */
    public Building() {
    }

    /**
     * Az osztály konstruktora
     *
     * @param name Az épület neve
     * @param classroomList A tantermek, amik az épületben vannak
     * @param id Az azonosító
     */
    public Building(String name, List<Classroom> classroomList, Integer id) {
        super(id);
        this.name = name;
        this.classroomList = classroomList;
    }

    /**
     * A BuildingDTO objektum Building objektummá konvertálását végrehajtó
     * megtódus
     *
     * @param buildingDTO A BuildingDTO objektum
     * @return A Building objektum
     */
    public static Building toBuilding(BuildingDTO buildingDTO) {
        return new Building(
                buildingDTO.getName(),
                Collections.emptyList()
        );
    }

}
