package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Status;
import RoomReservationSystem.repository.StatusRepository;
import RoomReservationSystem.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    
    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name);
    }
}
