package RoomReservationSystem.service;

import RoomReservationSystem.model.Status;

public interface StatusService {
    Status findByName(String name);
}
