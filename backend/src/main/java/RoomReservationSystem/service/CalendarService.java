package RoomReservationSystem.service;

import RoomReservationSystem.dto.CalendarEventDTO;
import java.util.List;

public interface CalendarService {

    List<CalendarEventDTO> getEvents();

    List<CalendarEventDTO> findByUserName(String name);

    List<CalendarEventDTO> findByBuildingName(String buildingName);

    List<CalendarEventDTO> findByClassroomName(String classroomName);

    List<CalendarEventDTO> findByEventName(String eventName);

    List<CalendarEventDTO> findBySubjectName(String subjectName);

    List<CalendarEventDTO> findBySemesterName(String semesterName);
}
