package RoomReservationSystem.api;

import static RoomReservationSystem.config.ApiErrorMessageConstants.ERROR_USER_CREATE;
import static RoomReservationSystem.config.ApiErrorMessageConstants.ERROR_USER_DELETE;
import static RoomReservationSystem.config.ApiErrorMessageConstants.ERROR_USER_UPDATE;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public Iterable<User> getAll(){
        return userService.findAll();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return userService.getNames();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByUsername")
    public User findByUsername(@RequestParam String username){
	return userService.findByUsername(username);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName")
    public List<User> findByName(@RequestParam String name){
	return userService.findByName(name);
    }
    
    
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            User registered = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registered);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ERROR_USER_CREATE);
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.delete( userService.findByUsername(user.getName()) );
            User saved = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ERROR_USER_UPDATE);
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deleteByUsername")
    public ResponseEntity deleteByUsername(@RequestParam String username) {
        if ( userService.findByUsername( username ) != null ) {
            userService.deleteByUsername(username);
            return new ResponseEntity(HttpStatus.OK);           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ERROR_USER_DELETE);
        }
    }   
}
