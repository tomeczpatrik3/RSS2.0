package RoomReservationSystem.service;

import RoomReservationSystem.dto.CalendarEventDTO;
import java.util.List;

public interface CalendarService {
    List<CalendarEventDTO> getEvents();
}
