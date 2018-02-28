package RoomReservationSystem.validation;

import RoomReservationSystem.model.User;
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
      return clazz == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty");
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");

        User user = (User) target;

        if ( this.userService.findByUsername(user.getUsername()) != null ) {
            errors.rejectValue("name", "user.username.alredyExists");
        }
        
        if ( this.userService.findByEmail(user.getEmail()) != null ) {
            errors.rejectValue("email", "user.email.alredyExists");
        }        
        
        if (user.getName() != null && user.getName().length() < 5 ||
                user.getName().length() > 30) {
            errors.rejectValue("name", "user.name.size");
        }
        
        if (user.getUsername() != null && user.getUsername().length() < 5 ||
                user.getUsername().length() > 30) {
            errors.rejectValue("name", "user.username.size");
        }
        
        if (user.getUsername() != null && user.getUsername().contains(" ")) {
            errors.rejectValue("name", "user.username.space");
        }

        if (user.getPassword() != null && user.getPassword().contains(" ")) {
            errors.rejectValue("password", "user.password.space");
        }

        if (user.getPassword() != null && user.getPassword().length() < 5 &&
                user.getPassword().length() > 30) {
            errors.rejectValue("password", "user.password.size");
        }

        if (user.getEmail() != null && user.getEmail().length() < 5 &&
                user.getEmail().length() > 50) {
            errors.rejectValue("email", "user.email.size");
        }

        if (user.getEmail() != null && !EMAIL_REGEX.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "user.email.invalid");
        }
  }
}
