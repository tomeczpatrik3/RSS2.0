package RoomReservationSystem.api;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.model.User;
import RoomReservationSystem.service.UserService;
import RoomReservationSystem.validation.UserValidator;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.USER_NOT_EXISTS;
import static RoomReservationSystem.config.ValidationErrorMessageConstants.concatErrors;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTOList;

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
    UserService userService;
    
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<UserDTO> getAll(){
        return toUserDTOList(userService.findAll());
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getNames")
    public List<String> getNames(){
        return userService.getNames();
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByUsername")
    public ResponseEntity findByUsername(@RequestParam String username){
	if (userService.findByUsername(username) != null)
            return ResponseEntity.ok(toUserDTO(userService.findByUsername(username)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(USER_NOT_EXISTS);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByName")
    public List<UserDTO> findByName(@RequestParam String name){
	return toUserDTOList(userService.findByName(name));
    }
    
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            User registered = userService.register(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toUserDTO(registered));
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
        }
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.delete( userService.findByUsername(userDTO.getName()) );
            User saved = userService.register(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);           
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(concatErrors(bindingResult));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(USER_NOT_EXISTS);
        }
    }   
}
