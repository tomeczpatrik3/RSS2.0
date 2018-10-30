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
     * @throws RoomReservationSystem.exception.AuthorityAlredyExistsException
     */
    @Override
    public Authority save(Authority authority) throws AuthorityAlredyExistsException {
//        if ( authorityRepository.findByName(authority.getName()) == null)
        return authorityRepository.save(authority);
//        else
//            throw new AuthorityAlredyExistsException(String.format("Ilyen névvel (%s) rendelkező engedély már létezik!", authority.getName().toUpperCase()));
    }

    /**
     * Az engedély név alapján történő keresését lehetővé tevő függvény
     *
     * @param name A keresendő engedély neve
     * @return A névnek megfelelő engedély ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException
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
     * Az engedély id alapján történő keresését lehetővé tevő függvény
     *
     * @param id A keresendő engedély id-je
     * @return Az id-nek megfelő engedély ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException
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
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException
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
