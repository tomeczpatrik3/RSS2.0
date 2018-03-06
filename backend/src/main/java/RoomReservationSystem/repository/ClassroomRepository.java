package RoomReservationSystem.repository;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> { 
    Classroom findById(int id);
    Classroom findByName(String name);
    List<Classroom> findByHasPc(boolean hasPC);
    List<Classroom> findByHasProjector(boolean hasProjector);
    List<Classroom> findByChairsLessThan(int number);
    List<Classroom> findByChairsGreaterThan(int number);
    List<Classroom> findByChairsBetween(int from, int to);
    List<Classroom> findByBuilding(Building building);
    void deleteByName(String name);
}
