package RoomReservationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A kontroller osztály ami átirányítja a megfelelő webes kéréseket az angular
 * számára
 *
 * @author Tomecz Patrik
 */
@Controller
public class ViewController {

    @RequestMapping({"/web/**", "/assets/**"})
    public String index() {
        return "forward:/index.html";
    }
}
