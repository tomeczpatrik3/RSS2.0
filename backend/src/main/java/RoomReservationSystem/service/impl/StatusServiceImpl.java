package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Status;
import RoomReservationSystem.repository.StatusRepository;
import RoomReservationSystem.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A státuszokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    
    /**
     * A státusz név alapján történő keresését megvalósító függvény
     * @param   name    A státusz neve
     * @return          A státusz ha létezik, null egyébként
     */
    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name);
    }
}
