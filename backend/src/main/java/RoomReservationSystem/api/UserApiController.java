package RoomReservationSystem.api;

import RoomReservationSystem.model.User;
import RoomReservationSystem.service.impl.UserServiceImpl;
import RoomReservationSystem.validation.UserValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/user")
public class UserApiController {
    
    @Autowired
    UserValidator userValidator;
    
    @Autowired
    UserServiceImpl userService;
    
    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public Iterable<User> getAll(){
        return userService.findAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return userService.getNames();
    }
    
    @GetMapping("/findByUsername")
    public User findByUsername(@RequestParam String username){
	return userService.findByUsername(username);
    }
    
    @GetMapping("/findByName")
    public List<User> findByName(@RequestParam String name){
	return userService.findByName(name);
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            User registered = userService.register(user);
            return ResponseEntity.ok(registered);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.delete( userService.findByUsername(user.getName()) );
            User saved = userService.register(user);
            return ResponseEntity.ok(saved);            
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping("/deleteByUsername")
    public ResponseEntity<User> deleteByUsername(@RequestParam String username) {
        if ( userService.findByUsername( username ) != null ) {
            userService.deleteByUsername(username);
            return new ResponseEntity(HttpStatus.OK);           
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }   
}
