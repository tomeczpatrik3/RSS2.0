package RoomReservationSystem.repository;

import RoomReservationSystem.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findById(int id);
    Authority findByName(String name);
}