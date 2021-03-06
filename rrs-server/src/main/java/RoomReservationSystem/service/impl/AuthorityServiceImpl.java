package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import RoomReservationSystem.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az engedélyekkel kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Az engedély mentésére szolgáló függvény
     *
     * @param authority Az engedély, amit szeretnénk eltárolni
     * @return Az eltárolt engedély
     */
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    /**
     * Az engedély név alapján történő keresését lehetővé tevő függvény
     *
     * @param name A keresendő engedély neve
     * @return A névnek megfelelő engedély
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Override
    public Authority findByName(String name) throws AuthorityNotExistsException {
        if (authorityRepository.findByName(name) != null) {
            return authorityRepository.findByName(name);
        } else {
            throw new AuthorityNotExistsException(String.format("Ilyen névvel (%s) rendelkező engedély nem létezik!", name));
        }
    }

    /**
     * Az engedély azonosító alapján történő keresését lehetővé tevő függvény
     *
     * @param id Az azonosító
     * @return Az azonosítónak megfelelő engedély
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Override
    public Authority findById(int id) throws AuthorityNotExistsException {
        if (authorityRepository.findById(id) != null) {
            return authorityRepository.findById(id);
        } else {
            throw new AuthorityNotExistsException(id);
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
        return authorityRepository.existsById(id);
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
        return authorityRepository.existsByName(name);
    }

    /**
     * Az engedély törlésére szolgáló függvény
     *
     * @param name Az engedély neve
     * @throws AuthorityNotExistsException A lehetséges kivétel, ha az engedély
     * nem létezik
     */
    @Override
    public void removeByName(String name) throws AuthorityNotExistsException {
        if (authorityRepository.findByName(name) != null) {
            authorityRepository.removeByName(name);
        } else {
            throw new AuthorityNotExistsException(name);
        }
    }
}
