package RoomReservationSystem.service;

import RoomReservationSystem.dto.reservation.ReservationEventDTO;
import java.util.List;

/**
 * A kalendár eseményekkel kapcsolatos műveletekért felelős interfész Részletes
 * információ a függvényekről a megválósításnál
 *
 * @author Tomecz Patrik
 */
public interface EventService {

    List<ReservationEventDTO> getEvents();

    List<ReservationEventDTO> findByUserName(String name);

    List<ReservationEventDTO> findByBuildingName(String buildingName);

    List<ReservationEventDTO> findByClassroomNameAndBuilding(String classroom, String building);

    List<ReservationEventDTO> findByEventName(String eventName);

    List<ReservationEventDTO> findBySubjectName(String subjectName);

    List<ReservationEventDTO> findBySemesterName(String semesterName);
}
