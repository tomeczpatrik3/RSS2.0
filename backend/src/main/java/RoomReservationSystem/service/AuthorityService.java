package RoomReservationSystem.service;

import RoomReservationSystem.model.Authority;

public interface AuthorityService {
    void delete(Authority authority);
    Authority save(Authority authority);
    Authority findByName(String name);
    Authority findById(int id);
}
