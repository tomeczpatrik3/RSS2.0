package RoomReservationSystem.model;

import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.dto.TypeDTO;
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
 * A foglalások típusait tartalmazó osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "types")
public class Type extends BaseEntity {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", unique=true)
    private String name; /*A típus neve*/    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Reservation> reservationList;  /*A típushoz tartozó foglalások listája*/
    
    /**
     * A TypeDTO objektum Type objektummá konvertálását végrehajtó megtódus
     * @param   typeDTO     A TypeDTO objektum
     * @return              A Type objektum
     */
    public static Type toType(TypeDTO typeDTO) {
        return new Type(
                typeDTO.getName(),
                Collections.emptyList()
        );
    }    
}
