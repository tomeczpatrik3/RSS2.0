package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.CalendarEventDTO;
import RoomReservationSystem.dto.reservation.ReservationInfoDTO;
import RoomReservationSystem.enums.Type;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import RoomReservationSystem.service.CalendarService;
import RoomReservationSystem.service.reservation.ClassReservationService;
import RoomReservationSystem.service.reservation.EventReservationService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private ClassReservationService classRService;

    @Autowired
    private EventReservationService eventRService;

    @Override
    public List<CalendarEventDTO> getEvents() {
        List<CalendarEventDTO> events = new ArrayList<>();
        try {
            events.addAll(generateEvents(classRService.findByStatus("ACCEPTED")));
            events.addAll(generateEvents(eventRService.findByStatus("ACCEPTED")));
        } catch (StatusNotExistsException ex) {
            Logger.getLogger(CalendarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    @Override
    public List<CalendarEventDTO> findByUserName(String name) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<CalendarEventDTO> findByBuildingName(String buildingName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getBuilding().equals(buildingName))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CalendarEventDTO> findByClassroomName(String classroomName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getClassroom().equals(classroomName))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CalendarEventDTO> findByEventName(String eventName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getEventName().equals(eventName))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CalendarEventDTO> findBySubjectName(String subjectName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getSubject().equals(subjectName))
                .collect(Collectors.toList());
    }
        
    @Override
    public List<CalendarEventDTO> findBySemesterName(String semesterName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getSemester().equals(semesterName))
                .collect(Collectors.toList());
    }

    private <T extends Reservation> List<CalendarEventDTO> generateEvents(List<T> reservations) {
        List<CalendarEventDTO> events = new ArrayList<>();

        reservations.forEach((reservation) -> {
            Type type;
            ReservationInfoDTO info;
            if (reservation instanceof ClassReservation) {
                type = Type.CLASS;
                info = new ReservationInfoDTO(
                        reservation.getId(),
                        type,
                        reservation.getUser().getName(),
                        reservation.getClassroom().getBuilding().getName(),
                        reservation.getClassroom().getName(),
                        ((ClassReservation) reservation).getSubject().getName(),
                        ((ClassReservation) reservation).getSemester().getName()
                );
            } else {
                type = Type.EVENT;
                info = new ReservationInfoDTO(
                        reservation.getId(),
                        type,
                        reservation.getUser().getName(),
                        reservation.getClassroom().getBuilding().getName(),
                        reservation.getClassroom().getName(),
                        ((EventReservation) reservation).getName()
                );
            }
            List<ReservationDate> dates = reservation.getDateList();
            dates.forEach((date) -> {
                events.add(new CalendarEventDTO(
                        date.getStart().toString(),
                        date.getEnd().toString(),
                        generateTitle(reservation),
                        info
                ));
            });
        });

        return events;
    }

    private <T extends Reservation> String generateTitle(T reservation) {
        String title;
        if (reservation instanceof ClassReservation) {
            title = ((ClassReservation) reservation).getSubject().getName();
        } else //else if (reservation instanceof EventReservation) 
        {
            title = ((EventReservation) reservation).getName();
        }
        return String.format(
                "%s - %s: %s",
                reservation.getClassroom().getBuilding().getName(),
                reservation.getClassroom().getName(),
                title
        );
    }
}
