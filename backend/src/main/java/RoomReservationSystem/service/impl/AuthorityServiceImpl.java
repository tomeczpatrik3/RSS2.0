package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import RoomReservationSystem.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az engedélyekkel kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    private AuthorityRepository authorityRepository;
    
    /**
     * Az engedély mentésére szolgáló függvény
     * @param   authority   Az engedély, amit szeretnénk eltárolni
     * @return              Az eltárolt engedély       
     */
    @Override
    public Authority save(Authority authority) {
        return this.authorityRepository.save(authority);
    }
    
    /**
     * Az engedély törlésére szolgáló függvény
     * @param authority Az engedély amit szeretnénk törölni
     */
    @Override
    public void delete(Authority authority) {
        this.authorityRepository.delete(authority);
    }
    
    /**
     * Az engedély név alapján történő keresését lehetővé tevő függvény
     * @param   name    A keresendő engedély neve
     * @return          A névnek megfelelő engedély ha létezik, null egyébként  
     */
    @Override
    public Authority findByName(String name) {
        return this.authorityRepository.findByName(name);
    }
    
    /**
     * Az engedély id alapján történő keresését lehetővé tevő függvény
     * @param   id  A keresendő engedély id-je
     * @return      Az id-nek megfelő engedély ha létezik, null egyébként
     */
    @Override
    public Authority findById(int id) {
        return this.authorityRepository.findById(id);
    }
}
