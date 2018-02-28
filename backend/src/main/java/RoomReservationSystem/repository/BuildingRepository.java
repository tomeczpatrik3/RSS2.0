package RoomReservationSystem.repository;

import RoomReservationSystem.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    void deleteByName(String name);
    
    Building findByName(String name);
}
