package RoomReservationSystem.service;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;

import java.util.List;

/**
 * Az osztálytermekkel kapcsolatos műveletekért felelős interfész
 * Részletes információ a függvényekről a megválósításnál
 * @author Tomecz Patrik
 */
public interface ClassroomService {
    void delete(Classroom classroom);
    void deleteByName(String name);
    Classroom save(Classroom classroom);
    Classroom findByNameAndBuildingName(String name, String buildingName);
    Classroom findById(int id);
    List<Classroom> findByName(String name);
    List<Classroom> findAll();
    List<Classroom> findByHasPc(boolean hasPC);
    List<Classroom> findByHasProjector(boolean hasProjector);
    List<Classroom> findByChairsLessThan(int num);
    List<Classroom> findByChairsGreaterThan(int num);
    List<Classroom> findByChairsBetween(int from, int to);
    List<Classroom> findByBuilding(Building building);
    List<String> getNamesByBuilding(String buildingName);
    List<String> getNames();
    Classroom findByDTO(ClassroomDTO classroomDTO);
}
