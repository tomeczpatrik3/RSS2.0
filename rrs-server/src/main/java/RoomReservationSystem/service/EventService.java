package RoomReservationSystem.service;

import RoomReservationSystem.dto.EventDTO;
import java.util.List;

/**
 * A kalendár eseményekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface EventService {

    List<EventDTO> getEvents();

    List<EventDTO> findByUserName(String name);

    List<EventDTO> findByBuildingName(String buildingName);

    List<EventDTO> findByClassroomNameAndBuilding(String classroom, String building);

    List<EventDTO> findByEventName(String eventName);

    List<EventDTO> findBySubjectName(String subjectName);

    List<EventDTO> findBySemesterName(String semesterName);
}
