package RoomReservationSystem.service;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomAlredyExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;

import java.util.List;

/**
 * Az osztálytermekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface ClassroomService {

    Classroom save(Classroom classroom) throws ClassroomAlredyExistsException;

    Classroom findByNameAndBuildingName(String name, String buildingName) throws ClassroomNotExistsException, BuildingNotExistsException;

    Classroom findById(int id) throws ClassroomNotExistsException;

    List<Classroom> findByName(String name);

    List<Classroom> findAll();

    List<Classroom> findByHasPc(boolean hasPC);

    List<Classroom> findByHasProjector(boolean hasProjector);

    List<Classroom> findByChairsLessThan(int num);

    List<Classroom> findByChairsGreaterThan(int num);

    List<Classroom> findByChairsBetween(int from, int to);

    List<Classroom> findByBuilding(Building building);

    List<Classroom> findByBuildingName(String buildingName) throws BuildingNotExistsException;

    List<String> getNamesByBuilding(String building) throws BuildingNotExistsException;

    Classroom findByDTO(ClassroomDTO classroomDTO) throws BuildingNotExistsException;

    boolean existsById(int id);

    boolean existsByNameAndBuilding(String name, Building building);

    void deleteByNameAndBuildingName(String name, String buildingName) throws ClassroomNotExistsException, BuildingNotExistsException;
}
