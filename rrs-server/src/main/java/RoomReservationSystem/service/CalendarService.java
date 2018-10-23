package RoomReservationSystem.service;

import RoomReservationSystem.dto.CalendarEventDTO;
import java.util.List;

/**
 * A kalendár eseményekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface CalendarService {

    List<CalendarEventDTO> getEvents();

    List<CalendarEventDTO> findByUserName(String name);

    List<CalendarEventDTO> findByBuildingName(String buildingName);

    List<CalendarEventDTO> findByClassroomNameAndBuilding(String classroom, String building);

    List<CalendarEventDTO> findByEventName(String eventName);

    List<CalendarEventDTO> findBySubjectName(String subjectName);

    List<CalendarEventDTO> findBySemesterName(String semesterName);
}
