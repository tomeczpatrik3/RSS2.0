package RoomReservationSystem.service;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.exception.BuildingAlredyExistsException;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.model.Building;

import java.util.List;

/**
 * Az épületekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface BuildingService {

    Building save(Building building) throws BuildingAlredyExistsException;

    Building findByName(String name) throws BuildingNotExistsException;

    Building findById(int id) throws BuildingNotExistsException;
    
    Building update(int id, Building building) throws BuildingNotExistsException;

    List<Building> findAll();

    List<String> getNames();

    Building findByDTO(BuildingDTO buildingDTO);

    boolean existsById(int id);

    boolean existsByName(String name);

    void deleteByName(String name) throws BuildingNotExistsException;
}
