package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.UserRepository;
import RoomReservationSystem.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserService   {
    
    private UserRepository userRepository;
    private AuthorityServiceImpl authorityService;
    private BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AuthorityServiceImpl authorityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.authorityService = authorityService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    /*
        Felhasználó regisztrálása titkosított jelszóval, megfelelő engedéllyel:
    */
    @Override
    public User register(UserDTO user) {
        Authority userAuth = this.authorityService.findByName("ROLE_USER");
        
        User registered = new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getName(),
                user.getEmail(),
                Arrays.asList(userAuth),
                Collections.emptyList()
        );
        
        userAuth.getUserList().add(registered);
        
        return userRepository.save(registered);
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User findById(int id) {
        return this.userRepository.findById(id);
    }
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
    
    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }
    
    @Override
    public void delete(User user){
        userRepository.delete(user);
    }
    
    @Override
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    
    @Override
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }
    
    @Override
    public List<String> getNames(){
        Iterable<User> users = this.findAll();
        List<String> names = new ArrayList<>();
        for (User user: users) {
            names.add(user.getName());
        }
        return names;
    }
}
