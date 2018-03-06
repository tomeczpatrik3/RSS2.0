package RoomReservationSystem.service;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import java.util.List;

public interface BuildingService {
    void delete(Building building);
    void deleteByName(String name);
    Building save(BuildingDTO building);
    Building findByName(String name);
    Building findById(int id);
    Iterable<Building> findAll();
    List<String> getNames();
}
