package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.EventDTO;
import RoomReservationSystem.dto.reservation.ReservationInfoDTO;
import RoomReservationSystem.enums.ReservationType;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.model.reservation.ClassReservation;
import RoomReservationSystem.model.reservation.EventReservation;
import RoomReservationSystem.model.reservation.Reservation;
import RoomReservationSystem.model.reservation.ReservationDate;
import RoomReservationSystem.service.reservation.ClassReservationService;
import RoomReservationSystem.service.reservation.EventReservationService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import RoomReservationSystem.service.EventService;

/**
 * A kalendár eseményekkel kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ClassReservationService classRService;

    @Autowired
    private EventReservationService eventRService;

    /**
     * Az összes esemény lekérdezésére szolgáló függvény.
     *
     * @return Az elfogadott foglalások egy listában eseménnyé konvertálva
     */
    @Override
    public List<EventDTO> getEvents() {
        List<EventDTO> events = new ArrayList<>();
        try {
            events.addAll(generateEvents(classRService.findByStatus("ACCEPTED")));
            events.addAll(generateEvents(eventRService.findByStatus("ACCEPTED")));
        } catch (StatusNotExistsException ex) {
            Logger.getLogger(EventServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    /**
     * Egy adott névhez tartozó események lekérdezését megvalósító függvény
     *
     * @param name A foglaló személy neve
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findByUserName(String name) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getName().equals(name))
                .collect(Collectors.toList());
    }

    /**
     * Egy adott épülethez tartozó események lekérdezését megvalósító függvény
     *
     * @param buildingName Az épület neve
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findByBuildingName(String buildingName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getBuilding().equals(buildingName))
                .collect(Collectors.toList());
    }

    /**
     * Egy adott épülethez és tanteremhez tartozó események lekérdezését
     * megvalósító függvény
     *
     * @param classroom A tanterem neve
     * @param building Az épület neve
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findByClassroomNameAndBuilding(String classroom, String building) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getClassroom().equals(classroom) && event.getInfo().getBuilding().equals(building))
                .collect(Collectors.toList());
    }

    /**
     * Egy adott névhez tartozó események lekérdezését megvalósító függvény
     *
     * @param eventName Az esemény neve
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findByEventName(String eventName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getEventName().equals(eventName))
                .collect(Collectors.toList());
    }

    /**
     * Egy adott tantárgyhoz tartozó események lekérdezését megvalósító függvény
     *
     * @param subjectName A tantárgy neve
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findBySubjectName(String subjectName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getSubject().equals(subjectName))
                .collect(Collectors.toList());
    }

    /**
     * Egy adott szemeszterhez tartozó események lekérdezését megvalósító
     * függvény
     *
     * @param semesterName A szemeszter
     * @return Az események egy listában
     */
    @Override
    public List<EventDTO> findBySemesterName(String semesterName) {
        return getEvents()
                .stream()
                .filter(event -> event.getInfo().getSemester().equals(semesterName))
                .collect(Collectors.toList());
    }

    /**
     * Az események generálásért felelős függény
     *
     * @param <T> A Reservation osztályból leszármazó típus
     * @param reservations A foglalások
     * @return A generált események egy listában
     */
    private <T extends Reservation> List<EventDTO> generateEvents(List<T> reservations) {
        List<EventDTO> events = new ArrayList<>();

        reservations.forEach((reservation) -> {
            ReservationType type;
            ReservationInfoDTO info;
            if (reservation instanceof ClassReservation) {
                type = ReservationType.CLASS;
                info = new ReservationInfoDTO(
                        reservation.getId(),
                        type,
                        reservation.getUser().getName(),
                        reservation.getClassroom().getBuilding().getName(),
                        reservation.getClassroom().getName(),
                        reservation.getNote(),
                        ((ClassReservation) reservation).getSubject().getName(),
                        ((ClassReservation) reservation).getSemester().getName()
                );
            } else {
                type = ReservationType.EVENT;
                info = new ReservationInfoDTO(
                        reservation.getId(),
                        type,
                        reservation.getUser().getName(),
                        reservation.getClassroom().getBuilding().getName(),
                        reservation.getClassroom().getName(),
                        reservation.getNote(),
                        ((EventReservation) reservation).getName()
                );
            }
            List<ReservationDate> dates = reservation.getDateList();
            dates.forEach((date) -> {
                events.add(new EventDTO(
                        date.getStart().toString(),
                        date.getEnd().toString(),
                        generateTitle(reservation),
                        info
                ));
            });
        });

        return events;
    }

    /**
     * Az esemény nevének generálásért felelős függvény
     *
     * @param <T> A Reservation osztályból leszármazó típus
     * @param reservation A foglalás
     * @return A generált név
     */
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
