package RoomReservationSystem.validation;

import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.service.UserService;
import static RoomReservationSystem.config.ErrorMessageConstants.*;

import static RoomReservationSystem.util.RegexUtils.isValidEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A felhasználók adatainak ellenőrzését végző osztály
 * @author Tomecz Patrik
 */
@Service
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == UserDTO.class;
    }

    /**
     * A validálást végző függvény
     * @param   target  Az objektum amit validálunk
     * @param   errors  A hibák, ha vannak
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty", USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty", NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty", PASSWORD_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty", EMAIL_EMPTY);

        UserDTO user = (UserDTO) target;

        /*Felhasználónlév és e-mail cím használatban létének ellenőrzése*/
        if ( this.userService.findByUsername(user.getUsername()) != null ) {
            errors.rejectValue("username", "user.username.alredyExists", USERNAME_ALREDY_EXISTS);
        }
        
        if ( this.userService.findByEmail(user.getEmail()) != null ) {
            errors.rejectValue("email", "user.email.alredyExists", EMAIL_ALREDY_EXISTS);
        }        
        
        /*A felhasználó nevének validálása*/
        if (user.getName() != null && user.getName().length() < 5 ||
                user.getName().length() > 30) {
            errors.rejectValue("name", "user.name.size", USER_NAME_SIZE);
        }
        
        /*Felhasználónév validálása*/
        if (user.getUsername() != null && user.getUsername().length() < 5 ||
                user.getUsername().length() > 30) {
            errors.rejectValue("username", "user.username.size", USERNAME_SIZE);
        }
        
        if (user.getUsername() != null && user.getUsername().contains(" ")) {
            errors.rejectValue("username", "user.username.space", USERNAME_SPACE);
        }

        /*Jelszó validálása*/
        if (user.getPassword() != null && user.getPassword().contains(" ")) {
            errors.rejectValue("password", "user.password.space", PASSWORD_SPACE);
        }

        if (user.getPassword() != null && user.getPassword().length() < 5 &&
                user.getPassword().length() > 30) {
            errors.rejectValue("password", "user.password.size", PASSWORD_SIZE);
        }

        /*E-mail cím validálása*/
        if (user.getEmail() != null && user.getEmail().length() < 5 &&
                user.getEmail().length() > 50) {
            errors.rejectValue("email", "user.email.size", EMAIL_SIZE);
        }

        if (user.getEmail() != null && !isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "user.email.invalidFormat", EMAIL_INVALID_FORMAT);
        }
  }
}
