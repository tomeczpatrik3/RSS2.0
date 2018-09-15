package RoomReservationSystem.util;

import org.springframework.validation.BindingResult;

/**
 * A validálással kapcsolatos segédfüggvényeket tartalmazo osztály
 *
 * @author Tomecz Patrik
 */
public class ValidationUtils {

    /**
     * A hibaüzenetek konkatenálását végző függvény
     *
     * @param bindingResult A validálás eredménye
     * @return A hibaüzenet(ek) szövegként
     */
    public static String concatErrors(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        errors.append("A validálás során az alábbi hiba/hibák történtek: \n");
        bindingResult.getAllErrors().forEach((err) -> {
            errors.append(err.getDefaultMessage());
        });
        return errors.toString();
    }
}
