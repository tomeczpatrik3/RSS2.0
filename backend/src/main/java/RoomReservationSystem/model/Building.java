package RoomReservationSystem.model;

import RoomReservationSystem.dto.BuildingDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

import javax.persistence.Basic;
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
import lombok.NoArgsConstructor;

/**
 * Épület entitás
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", unique=true)
    private String name; /*Az épület neve*/
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    private List<Classroom> classroomList;  /*Az épülethez tartozó tantermek listája*/
    
    /**
     * A BuildingDTO objektum Building objektummá konvertálását végrehajtó megtódus
     * @param   buildingDTO     A BuildingDTO objektum
     * @return                  A Building objektum
     */
    public static Building toBuilding(BuildingDTO buildingDTO) {
        return new Building(
                buildingDTO.getName(),
                Collections.emptyList()
        );
    }

}
