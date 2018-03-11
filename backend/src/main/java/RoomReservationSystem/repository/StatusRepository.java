package RoomReservationSystem.repository;

import RoomReservationSystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findById(int id);
    Status findByName(String name);
}