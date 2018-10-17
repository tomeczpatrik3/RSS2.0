package RoomReservationSystem.api;

import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTOList;
import RoomReservationSystem.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarApiController {

    @Autowired
    private CalendarService calendarService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity getEvents() {
        return ResponseEntity.ok(calendarService.getEvents());
    }
}
