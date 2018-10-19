package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.CalendarEventDTO;
import RoomReservationSystem.dto.ReservationInfoDTO;
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

    private <T extends Reservation> List<CalendarEventDTO> generateEvents(List<T> reservations) {
        List<CalendarEventDTO> events = new ArrayList<>();

        reservations.forEach((reservation) -> {
            Type type;
            if (reservation instanceof ClassReservation) {
                type = Type.CLASS;
            } else {
                type = Type.EVENT;
            }
            List<ReservationDate> dates = reservation.getDateList();
            dates.forEach((date) -> {
                events.add(new CalendarEventDTO(
                        date.getStart().toString(),
                        date.getEnd().toString(),
                        generateTitle(reservation),
                        new ReservationInfoDTO(
                                reservation.getId(),
                                type,
                                reservation.getUser().getName()
                        )
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
