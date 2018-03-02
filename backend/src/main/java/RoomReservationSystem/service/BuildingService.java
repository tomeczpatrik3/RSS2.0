package RoomReservationSystem.service;

import RoomReservationSystem.model.Building;
import java.util.List;

public interface BuildingService {
    void delete(Building building);
    void deleteByName(String name);
    Building save(Building building);
    Building findByName(String name);
    Building findById(int id);
    Iterable<Building> findAll();
    List<String> getNames();
}
