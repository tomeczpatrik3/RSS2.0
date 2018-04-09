package RoomReservationSystem.dto;

import RoomReservationSystem.model.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A tantárgyakhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO {
    private String name;    /*A típus neve*/
    
    /**
     * A Type objektumból TypeDTO objektum létrehozásáért felelős metódus
     * @param   type     A Type objektum
     * @return           A TypeDTO objektum
     */
    public static TypeDTO toTypeDTO(Type type) {
        return new TypeDTO(type.getName());
    }
    
    /**
     * Több Type objektum TypeDTO objektummá alakításáért felelős metódus
     * @param   types    A Type objektumok egy listában
     * @return           A TypeDTO objektumok egy listában
     */
    public static List<TypeDTO> toTypeDTOList(List<Type> types) {
        List<TypeDTO> TypeDTOs = new ArrayList<>();
        types.forEach((type) -> {
            TypeDTOs.add(toTypeDTO(type));
        });
        return TypeDTOs;
    }
}