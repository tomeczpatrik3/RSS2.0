package RoomReservationSystem.validation;

import static RoomReservationSystem.config.ValidationErrorMessageConstants.*;
import RoomReservationSystem.dto.UserDTO;
import RoomReservationSystem.service.impl.UserServiceImpl;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");
    
    @Autowired
    private UserServiceImpl userService;
    
    @Override
    public boolean supports(Class<?> clazz) {
      return clazz == UserDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty", USER_USERNAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty", USER_NAME_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty", USER_PASSWORD_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty", USER_EMAIL_EMPTY);

        UserDTO user = (UserDTO) target;

        if ( this.userService.findByUsername(user.getUsername()) != null ) {
            errors.rejectValue("username", "user.username.alredyExists", USER_ALREDY_EXISTS);
        }
        
        if ( this.userService.findByEmail(user.getEmail()) != null ) {
            errors.rejectValue("email", "user.email.alredyExists", USER_EMAIL_ALREDY_EXISTS);
        }        
        
        if (user.getName() != null && user.getName().length() < 5 ||
                user.getName().length() > 30) {
            errors.rejectValue("name", "user.name.size", USER_NAME_SIZE);
        }
        
        if (user.getUsername() != null && user.getUsername().length() < 5 ||
                user.getUsername().length() > 30) {
            errors.rejectValue("username", "user.username.size", USER_USERNAME_SIZE);
        }
        
        if (user.getUsername() != null && user.getUsername().contains(" ")) {
            errors.rejectValue("username", "user.username.space", USER_USERNAME_SPACE);
        }

        if (user.getPassword() != null && user.getPassword().contains(" ")) {
            errors.rejectValue("password", "user.password.space", USER_PASSWORD_SPACE);
        }

        if (user.getPassword() != null && user.getPassword().length() < 5 &&
                user.getPassword().length() > 30) {
            errors.rejectValue("password", "user.password.size", USER_PASSWORD_SIZE);
        }

        if (user.getEmail() != null && user.getEmail().length() < 5 &&
                user.getEmail().length() > 50) {
            errors.rejectValue("email", "user.email.size", USER_EMAIL_SIZE);
        }

        if (user.getEmail() != null && !EMAIL_REGEX.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "user.email.invalid", USER_EMAIL_INVALID);
        }
  }
}
