package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.model.Authority;
import RoomReservationSystem.model.User;
import RoomReservationSystem.repository.UserRepository;
import RoomReservationSystem.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static RoomReservationSystem.model.User.toUser;
import java.util.Arrays;

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
    public User register(UserDTO userDTO) {
        Authority userAuth = this.authorityService.findByName("ROLE_USER");
        
        User user = toUser(
                userDTO, 
                passwordEncoder.encode(userDTO.getPassword()),
                Arrays.asList(userAuth)
        );
        
        userAuth.addUser(user);
        
        return userRepository.save(user);
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
    public User findByUsername(String username) throws UsernameNotFoundException{
        if (userRepository.findByUsername(username)!=null)
            return userRepository.findByUsername(username);
        else
            throw new UsernameNotFoundException(username);
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
