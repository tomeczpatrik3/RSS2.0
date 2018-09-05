package RoomReservationSystem.service.impl;

import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.exception.InvalidParameterException;
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
     * @throws RoomReservationSystem.exception.InvalidParameterException           
     */
    @Override
    public Authority save(Authority authority) throws InvalidParameterException{
        if (authority == null)
            throw new InvalidParameterException("Null paraméter!");
        else
            return authorityRepository.save(authority);
    }
    
    /**
     * Az engedély törlésére szolgáló függvény
     * @param authority Az engedély amit szeretnénk törölni
     * @throws RoomReservationSystem.exception.InvalidParameterException
     */
    @Override
    public void delete(Authority authority) throws InvalidParameterException{
        if (authority == null)
            throw new InvalidParameterException("Null paraméter!");
        else
            authorityRepository.delete(authority);
    }
    
    /**
     * Az engedély név alapján történő keresését lehetővé tevő függvény
     * @param   name    A keresendő engedély neve
     * @return          A névnek megfelelő engedély ha létezik, null egyébként  
     * @throws RoomReservationSystem.exception.InvalidParameterException  
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException  
     */
    @Override
    public Authority findByName(String name) throws InvalidParameterException, AuthorityNotExistsException{
        if (name == null)
            throw new InvalidParameterException("Null paraméter!");
        else if ( authorityRepository.findByName(name) != null)
            return authorityRepository.findByName(name);
        else
            throw new AuthorityNotExistsException("Ilyen névvel rendelkező engedély nem létezik!");
    }
    
    /**
     * Az engedély id alapján történő keresését lehetővé tevő függvény
     * @param   id  A keresendő engedély id-je
     * @return      Az id-nek megfelő engedély ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException
     */
    @Override
    public Authority findById(int id) throws AuthorityNotExistsException{
        if (authorityRepository.findById(id) != null)
            return authorityRepository.findById(id);
        else
            throw new AuthorityNotExistsException("Ilyen azonosítóval rendelkező engedély nem létezik!");
    }
}
