package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.repository.StatusRepository;
import RoomReservationSystem.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A státuszokkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    /**
     * A státusz név alapján történő keresését megvalósító függvény
     *
     * @param name A státusz neve
     * @return A státusz ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.StatusNotExistsException
     */
    @Override
    public Status findByName(String name) throws StatusNotExistsException {
        Status found = statusRepository.findByName(name);
        if (found != null) {
            return found;
        } else {
            throw new StatusNotExistsException(String.format("Ilyen névvel (%s) rendelkező státusz nem létezik!", name));
        }
    }
}
