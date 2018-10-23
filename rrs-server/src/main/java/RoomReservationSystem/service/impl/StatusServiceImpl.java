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

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott azonosítóhoz
     * tartozó entitás
     *
     * @param id Az azonosító
     * @return Igen, ha létezik az azonosítóhoz tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsById(int id) {
        return statusRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott névhez tartozó
     * entitás
     *
     * @param name A név
     * @return Igen, ha létezik a névhez tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByName(String name) {
        return statusRepository.existsByName(name);
    }
}
