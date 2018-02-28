package RoomReservationSystem.service.impl;

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

@Service
public class UserServiceImpl implements UserService   {
    
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
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
        Felhasználó regisztrálása titkosított jelszóval
    */
    @Override
    public User register(User user) {
        user.setAuthorityList(new ArrayList<Authority>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
