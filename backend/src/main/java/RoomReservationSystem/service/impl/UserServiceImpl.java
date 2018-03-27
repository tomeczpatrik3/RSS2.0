package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.UserRepository;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.service.AuthorityService;
import static RoomReservationSystem.model.User.toUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * A felhasználókkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class UserServiceImpl implements UserService   {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthorityService authorityService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * A felhasználó betöltését/megkeresését megvalósító függvény
     * @param   username                    A kívánt felhasználó felhasználóneve
     * @return                              A felhasználó ha létezik
     * @throws UsernameNotFoundException    Ha nem létezik ez a felhasználónév
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    /**
     * A felhasználó regisztrálását megvalósító függvény
     * A felhasználó jelszavának titkosítása BCryptPasswordEncoder segítéségével történik
     * A felhasználó default engedélye pedig a ROLE_USER lesz
     * @param   userDTO A felhasználó adatai
     * @return          A regisztrált felhasználó
     */
    @Override
    public User register(UserDTO userDTO) {
        Authority userAuth = authorityService.findByName("ROLE_USER");
        if (userAuth==null) {
            userAuth = authorityService.save(new Authority("ROLE_USER", Collections.emptyList()));
        }
        
        User user = userRepository.save(toUser(
                userDTO, 
                passwordEncoder.encode(userDTO.getPassword()),
                Arrays.asList(userAuth)
        ));
        
        userAuth.addUser(user);
        
        return user;
    }
    
    /**
     * A felhasználó e-mail cím alapján történő megkeresését megvalósító függvény
     * @param   email   A felhasználó e-mail címe
     * @return          A felhasználó ha létezik, null egyébként
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * A felhasználó id alapján történő megkeresését megvalósító függvény
     * @param   id      A felhasználó id-ja
     * @return          A felhasználó ha létezik, null egyébként
     */
    @Override
    public User findById(int id) {
        return this.userRepository.findById(id);
    }
    
    /**
     * A felhasználó felhasználónév alapján történő megkeresését megvalósító függvény
     * @param   username    A felhasználó felhasználóneve
     * @return              A felhasználó ha létezik, null egyébként
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
     /**
     * A felhasználó felhasználónév alapján történő törlését megvalósító függvény
     * @param   username    A felhasználó felhasználóneve
     */
    @Override
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
    
    /**
     * A felhasználó törlését megvalósító függvény
     * @param   user    A törleni kívánt felhasználó
     */
    @Override
    public void delete(User user){
        userRepository.delete(user);
    }
    
    /**
     * A felhasználók lekérdezését megvalósító függvény
     * @return  A felhasználók egy listában
     */
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    /**
     * A felhasználók név alapján történő keresését megvalósító függvény
     * @param   name    A felhasználó neve
     * @return          Az adott névvel rendelkező felhasználó(k) egy listában
     */
    @Override
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }
    
    /**
     * A felhasználók neveinek lekérdezését megvalósító függvény
     * @return  A felhasználók nevei egy listában
     */
    @Override
    public List<String> getNames(){
        Iterable<User> users = this.findAll();
        List<String> names = new ArrayList<>();
        for (User user: users) {
            names.add(user.getName());
        }
        return names;
    }
    
    /**
     * A DTO objektum alapján történő keresést megvalósító függvény
     * (Annak ismeretében hogy melyik attribútum egyedi)
     * @param   userDTO    A DTO objektum
     * @return             A User objektum ha létezik
     */
    @Override
    public User findByDTO(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername());
    }  
}
