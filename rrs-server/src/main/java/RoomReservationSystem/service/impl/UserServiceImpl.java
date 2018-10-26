package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.exception.AuthorityAlredyExistsException;
import RoomReservationSystem.exception.AuthorityNotExistsException;
import RoomReservationSystem.exception.UserAlredyExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * A felhasználókkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * A felhasználó betöltését/megkeresését megvalósító függvény
     *
     * @param username A kívánt felhasználó felhasználóneve
     * @return A felhasználó ha létezik
     * @throws UsernameNotFoundException Ha nem létezik ez a felhasználónév
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
     * A felhasználó regisztrálását megvalósító függvény A felhasználó
     * jelszavának titkosítása BCryptPasswordEncoder segítéségével történik A
     * felhasználó default engedélye pedig a ROLE_USER lesz
     *
     * @param userDTO A felhasználó adatai
     * @return A regisztrált felhasználó
     * @throws RoomReservationSystem.exception.UserAlredyExistsException
     * @throws RoomReservationSystem.exception.AuthorityNotExistsException
     * @throws RoomReservationSystem.exception.AuthorityAlredyExistsException
     */
    @Override
    public User register(UserDTO userDTO) throws UserAlredyExistsException, AuthorityNotExistsException, AuthorityAlredyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlredyExistsException(String.format("Ilyen felhasználónévvel (%s) rendelkező felhasználó már létezik!", userDTO.getUsername()));
        } else if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserAlredyExistsException(String.format("Ilyen e-mail címmel (%s) rendelkező felhasználó már létezik!", userDTO.getEmail()));
        } else {
            Authority userAuth = authorityService.findByName("ROLE_USER");

            User user = userRepository.save(toUser(
                    userDTO,
                    passwordEncoder.encode(userDTO.getPassword()),
                    Arrays.asList(userAuth)
            ));

            userAuth.addUser(user);
            authorityService.save(userAuth);

            return user;
        }
    }

    /**
     * A felhasználó azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param user A felhasználó
     * @return A frissített felhasználó
     * @throws UserNotExistsException
     */
    @Override
    public User update(int id, User user) throws UserNotExistsException {
        if (userRepository.findById(id) == null) {
            throw new UserNotExistsException(String.format("Ilyen azonosítóval (%d) rendelkező felhasználó nem létezik!", id));
        } else {
            user.setId(id);
            return userRepository.save(user);
        }
    }

    /**
     * A felhasználó e-mail cím alapján történő megkeresését megvalósító
     * függvény
     *
     * @param email A felhasználó e-mail címe
     * @return A felhasználó ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public User findByEmail(String email) throws UserNotExistsException {
        User found = userRepository.findByEmail(email);
        if (found != null) {
            return found;
        } else {
            throw new UserNotExistsException(String.format("Ilyen e-mail címmel (%s) rendelkező felhasználó nem létezik!", email));
        }
    }

    /**
     * A felhasználó id alapján történő megkeresését megvalósító függvény
     *
     * @param id A felhasználó id-ja
     * @return A felhasználó ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public User findById(int id) throws UserNotExistsException {
        User found = userRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new UserNotExistsException(String.format("Ilyen azonosítóval (%d) rendelkező felhasználó nem létezik!", id));
        }
    }

    /**
     * A felhasználó felhasználónév alapján történő megkeresését megvalósító
     * függvény
     *
     * @param username A felhasználó felhasználóneve
     * @return A felhasználó ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public User findByUsername(String username) throws UserNotExistsException {
        User found = userRepository.findByUsername(username);
        if (found != null) {
            return found;
        } else {
            throw new UserNotExistsException(String.format("Ilyen felhasználónévvel (%s) rendelkező felhasználó nem létezik!", username));
        }
    }

    /**
     * A felhasználó felhasználónév alapján történő törlését megvalósító
     * függvény
     *
     * @param username A felhasználó felhasználóneve
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public void deleteByUsername(String username) throws UserNotExistsException {
        User found = userRepository.findByUsername(username);
        if (found != null) {
            found.getAuthorityList().forEach((authority) -> {
                authority.removeUser(found);
            });

            userRepository.deleteByUsername(username);
        } else {
            throw new UserNotExistsException(String.format("Ilyen felhasználónévvel (%s) rendelkező felhasználó nem létezik!", username));
        }
    }

    /**
     * A felhasználók lekérdezését megvalósító függvény
     *
     * @return A felhasználók egy listában
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * A felhasználók név alapján történő keresését megvalósító függvény
     *
     * @param name A felhasználó neve
     * @return Az adott névvel rendelkező felhasználó(k) egy listában
     */
    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * A felhasználók neveinek lekérdezését megvalósító függvény
     *
     * @return A felhasználók nevei egy listában
     */
    @Override
    public List<String> getNames() {
        Iterable<User> users = this.findAll();
        List<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getName());
        }
        return names;
    }

    /**
     * A DTO objektum alapján történő keresést megvalósító függvény (Annak
     * ismeretében hogy melyik attribútum egyedi)
     *
     * @param userDTO A DTO objektum
     * @return A User objektum ha létezik
     */
    @Override
    public User findByDTO(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername());
    }

    /**
     * Egy adott felhasználónévhez tartozó teljes név lekérdezését megvalósító
     * függvény
     *
     * @param username A felhasználónév
     * @return A felhasználónévhez tartozó teljes név
     * @throws RoomReservationSystem.exception.UserNotExistsException
     */
    @Override
    public Map<String, String> getName(String username) throws UserNotExistsException {
        return Collections.singletonMap("name", findByUsername(username).getName());
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
        return userRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott felhasználónévhez
     * tartozó entitás
     *
     * @param username A felhasználónév
     * @return Igen, ha létezik a felhasználónévhez tartozó entitás, nem
     * egyébként
     */
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott e-mail címhez
     * tartozó entitás
     *
     * @param email Az e-mail cím
     * @return Igen, ha létezik az e-mail címhez tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
