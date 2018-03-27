package RoomReservationSystem.service;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;

import java.util.List;

/**
 * Az épületekkel kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface BuildingService {
    void delete(Building building);
    void deleteByName(String name);
    Building save(Building building);
    Building findByName(String name);
    Building findById(int id);
    List<Building> findAll();
    List<String> getNames();
    Building findByDTO(BuildingDTO buildingDTO);
}
