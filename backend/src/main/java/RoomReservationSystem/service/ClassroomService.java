package RoomReservationSystem.service;

import RoomReservationSystem.model.Classroom;
import java.util.List;

public interface ClassroomService {
    void delete(Classroom classroom);
    void deleteByName(String name);
    void deleteAll();
    Classroom save(Classroom classroom);
    Iterable<Classroom> findAll();
    Classroom findByName(String name);
    List<Classroom> findByHasPc(boolean hasPC);
    List<Classroom> findByHasProjector(boolean hasProjector);
    List<Classroom> findByChairsLessThan(int num);
    List<Classroom> findByChairsGreaterThan(int num);
    List<Classroom> findByChairsBetween(int from, int to);
    List<String> getNames();
}
